/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.personasDAO;
import VO.personasVO;
import VO.tipodedocumentoVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author wilson
 */
public class controllerPersona extends HttpServlet {

    private personasDAO personaAO = new personasDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            int opcion = 0;
            if (request.getParameter("opcion") != null) {
                opcion = Integer.parseInt(request.getParameter("opcion"));
            }
            switch (opcion) {
                case 0://mostrar personas Listar
                    out.println(listadopersonas());
                    break;
                case 1:

                    break;
                case 2://Registrar

                    //ojo cuidado me troleo varias veces esta meirda los campos que aqui se dan tienen que ser iguales a los
                    //que pusieron en su formularion no el de las validaciones.
                    long idpersona = Long.parseLong(request.getParameter("documento"));
                    int tipodocumento = Integer.parseInt(request.getParameter("tipodedocumento"));
                    String primernombre = request.getParameter("primernombre");
                    String segundonombre = request.getParameter("segundonombre");
                    String primerapellido = request.getParameter("primerapellido");
                    String segundoapellido = request.getParameter("segundoapellido");
                    String genero = request.getParameter("Genero");
                    String direccion = request.getParameter("direccion");
                    long telefono = Long.parseLong(request.getParameter("telefono"));
                    String correo = request.getParameter("correo");
                    String estadocivil = request.getParameter("estadocivil");
                    Date fechadenacimiento = Date.valueOf(request.getParameter("fechanacimiento"));
                    response.sendRedirect("Vortal/guest_persona/Registrar.jsp?confir=" + registrarPersona(idpersona, tipodocumento, primernombre, segundonombre, primerapellido, segundoapellido, genero, direccion, telefono, correo, estadocivil, fechadenacimiento));
//                    
                    break;
                case 3://aqui se consulta si se va eliminar o modificar
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String apellidoBuscar = (String) request.getParameter("nombre");
                    out.println(consultaPersona(apellidoBuscar, opcionconsulta));
                    break;
                case 4:
                    //modificar

                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        int idperson = Integer.parseInt(request.getParameter("perlid"));
                        personasVO rlVO = new personasVO();
                        rlVO = personaAO.consultaIndividualPersona(idperson);//consulto para atraermo los atributos
                        session.setAttribute("personaChange", rlVO);
                        out.println(personaAModificar(rlVO));
                    }
                    if (opcionmodificar == 2) {
                        personasVO rlVO = new personasVO();
                        rlVO = (personasVO) session.getAttribute("personaChange");
                        rlVO.setid_documento(Integer.parseInt(request.getParameter("iddocumento")));
                        rlVO.setprimernombre_persona(request.getParameter("primernombre"));
                        rlVO.setsegundonombre_persona(request.getParameter("segundonombre"));
                        rlVO.setprimerapellido_persona(request.getParameter("primerapellido"));
                        rlVO.setsegundoapellido_persona(request.getParameter("segundoapellido"));
                        rlVO.setgenero_persona(request.getParameter("genero"));
                        rlVO.setdireccion_persona(request.getParameter("direccion"));
                        rlVO.settelefono_persona(Long.parseLong(request.getParameter("telefono")));
                        rlVO.setcorreo_persona(request.getParameter("correo"));
                        rlVO.setestadocivil_persona(request.getParameter("estadocivil"));
                        rlVO.setfechanacimiento_persona(Date.valueOf(request.getParameter("fechan")));
                        boolean respuestaModificar = (boolean) personaAO.modificarPersona(rlVO);
                        if (respuestaModificar) {

                            session.removeAttribute("personaChange");
                            response.sendRedirect("Vortal/guest_persona/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_persona/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar

                    long elim = Long.parseLong(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarPersona(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_persona/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_persona/Eliminar.jsp?confir=error");
                    }
                    break;

                case 6://BUSCARPERSONASID
                    out.println(documentos());
                    break;
                case 7://BUSCARdocuentos
                    out.println(tipodocumentos());
                    break;
            }
        } finally {
            out.close();
        }
    }

    private String documentos() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = personaAO.listardocumentos();
        if (!datos.isEmpty()) {
            personasVO tidoVO = new personasVO();
            for (Object dato : datos) {
                tidoVO = (personasVO) dato;
                opcion += "<option value=\"" + tidoVO.getid_persona() + "\">" + tidoVO.getid_persona() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String tipodocumentos() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = personaAO.listartipodedocumentos();
        if (!datos.isEmpty()) {
            tipodedocumentoVO tidoVO = new tipodedocumentoVO();
            for (Object dato : datos) {
                tidoVO = (tipodedocumentoVO) dato;
                opcion += "<option value=\"" + tidoVO.getid_documento() + "\">" + tidoVO.getnombre_documento() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String listadopersonas() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = personaAO.listarpersonas();
        if (!datos.isEmpty()) {
            personasVO personVO = new personasVO();
            for (Object dato : datos) {
                personVO = (personasVO) dato;
                if (personVO.getid_documento() == 1) {
                    x = "Cedula de Ciudadania";
                } else if (personVO.getid_documento() == 2) {
                    x = "Tarjeta Identidad";
                } else if (personVO.getid_documento() == 3) {
                    x = "Pasaporte";
                }
                listado += "<tr>"
                        + "<td>" + x + "</td>"
                        + "<td>" + personVO.getid_persona() + "</td>"
                        + "<td>" + personVO.getprimernombre_persona() + "</td>"
                        + "<td>" + personVO.getprimerapellido_persona() + "</td>"
                        + "<td>" + personVO.getgenero_persona() + "</td>"
                        + "<td>" + personVO.getcorreo_persona() + "</td>"
                        + "<td>" + personVO.getestadocivil_persona() + "</td>"
                        + "<form role=\"form\" method=\"post\" id=\"eliminarPersona\" name=\"eliminarPersona\" enctype=\"application/x-www-form-urlencoded\">"
                        + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"3\"/>" + "<td>" + "<button class=\"btn btn-primary btn-xs\"><i class=\"fa fa-pencil\"></i></button>" + "<button class=\"btn btn-danger btn-xs\" id=\"opcion\" name=\"opcion\" value=\"3\"><i class=\"fa fa-trash-o\" onclick=\"confirmarEliminarPersona();\"></i></button>"
                        + "</td>"
                        + "</tr>";
            }
        } else {
            listado += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        //mensaje para confirmar si lo va eliminar 

        return listado;
    }

    private String registrarPersona(long idpersona, int tipodocumento, String primernombre, String segundonombre, String primerapellido, String segundoapellido, String genero, String direccion, long telefono, String correo, String estadocivil, Date fechadenaciemiento) throws SQLException {

        boolean registro = false;

        String message = "";
        registro = (boolean) personaAO.registrarPersona(idpersona, tipodocumento, primernombre, segundonombre, primerapellido, segundoapellido, genero, direccion, telefono, correo, estadocivil, fechadenaciemiento);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String consultaPersona(String Apellido, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = personaAO.consultarPersona(Apellido);

        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarPersona\" name=\"eliminarPersona\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";

        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarPersona\" name=\"modificarPersona\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Identificador"
                + "</th>"
                + "<th>"
                + "Primer Nombre"
                + "</th>"
                + "<th>"
                + "Segundo Nombre"
                + "</th>"
                + "<th>"
                + "Primer Apellido"
                + "</th>"
                + "<th>"
                + "Segundo Apellido"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";

        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                personasVO rlVO = new personasVO();
                rlVO = (personasVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getid_persona() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"perlid\" type=\"radio\" name=\"perlid\" value=\"" + rlVO.getid_persona() + "\" onchange='javascript: personaAModificar(" + rlVO.getid_persona() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getid_persona() + "</td>"
                        + "<td>" + rlVO.getprimernombre_persona() + "</td>"
                        + "<td>" + rlVO.getsegundonombre_persona() + "</td>"
                        + "<td>" + rlVO.getprimerapellido_persona() + "</td>"
                        + "<td>" + rlVO.getsegundoapellido_persona() + "</td>"
                        + "</tr>";

            }
        } else {
            consulta += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        consulta += "</tbody>"
                + "</table>"
                + "</div>"
                + "</form>";
        if (opcionconsulta == 1) {
            consulta += "<button type=\"button\" class=\"btn btn-success\" onclick=\"confirmarEliminarpersona();\">Eliminar Registro</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"seleccionPersonaModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String personaAModificar(personasVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"personaModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"personaModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>ID Persona</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idpersona\" name=\"idpersona\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getid_persona() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Tipo De Documento</label>"
                + " <select class=\"form-control\" name=\"iddocumento\" id=\"iddocumento\"> ";
        LinkedList xd = new LinkedList();
        xd = personaAO.listartipodedocumentos();
        if (!xd.isEmpty()) {
            tipodedocumentoVO persVO = new tipodedocumentoVO();
            for (Object xd1 : xd) {
                persVO = (tipodedocumentoVO) xd1;
                if (persVO.getid_documento() == rlVO.getid_documento()) {
                    formulario += "<option value=\"" + persVO.getid_documento() + "\" selected>" + persVO.getnombre_documento() + "</option>";
                } else {
                    formulario += "<option value=\"" + persVO.getid_documento() + "\">" + persVO.getnombre_documento() + "</option>";
                }
            }
        }
        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Primer Nombre</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"primernombre\" name=\"primernombre\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getprimernombre_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>segundo Nombre</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"segundonombre\" name=\"segundonombre\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getsegundonombre_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Primer Apellido</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"primerapellido\" name=\"primerapellido\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getprimerapellido_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Segundo Apellido</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"segundoapellido\" name=\"segundoapellido\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getsegundoapellido_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Genero</label>"
                + "<select class=\"form-control\" id=\"genero\" name=\"genero\">";

        if (rlVO.getgenero_persona().equalsIgnoreCase("Masculino")) {
            formulario += "<option value=\"Masculino\" selected>Maculino</option>"
                    + "<option value=\"Femenino\">Femenino</option>";
        } else if (rlVO.getgenero_persona().equalsIgnoreCase("Femenino")) {
            formulario += "<option value=\"Femenino\" selected>Femenino</option>"
                    + "<option value=\"Masculino\">Masculino</option>";
        } else {
            formulario += "<option value=\"Masculino\">Masculino</option>"
                    + "<option value=\"Femenino\">Femenino</option>";
        }

        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Direccion</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"direccion\" name=\"direccion\" placeholder=\"Digita el o modifica la direccion\" value=\"" + rlVO.getdireccion_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>telefono</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"telefono\" name=\"telefono\" placeholder=\"Digita el o modifica direccion\" value=\"" + rlVO.gettelefono_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Correo</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"correo\" name=\"correo\" placeholder=\"Digita el o modificar correo\" value=\"" + rlVO.getcorreo_persona() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Estado Civil</label>"
                + "<select class=\"form-control\" id=\"estadocivil\" name=\"estadocivil\">";

        if (rlVO.getestadocivil_persona().equalsIgnoreCase("Casado")) {
            formulario += "<option value=\"Casado\" selected>Casado</option>"
                    + "<option value=\"Soltero\">Soltero</option>";
        } else if (rlVO.getestadocivil_persona().equalsIgnoreCase("Soltero")) {
            formulario += "<option value=\"Soltero\" selected>Soltero</option>"
                    + "<option value=\"Casado\">Casado</option>";
        } else {
            formulario += "<option value=\"Soltero\">Soltero</option>"
                    + "<option value=\"Casado\">Casado</option>";
        }


        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Fecha Nacimiento</label>"
                + "<input type=\"date\" class=\"form-control\" id=\"fechan\" name=\"fechan\" placeholder=\"Digita el o modifica fecha\" value=\"" + rlVO.getfechanacimiento_persona() + "\" required>"
                + "</div>"
                + "</form>";
        return formulario;
    }

    private boolean eliminarPersona(long idpersona) throws SQLException {
        return personaAO.eliminarPersona(idpersona);
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(controllerPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(controllerPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
