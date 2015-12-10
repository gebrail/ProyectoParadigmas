/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.grupoDAO;
import VO.grupo;
import VO.materiaVO;
import VO.personasVO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class controllerGrupo extends HttpServlet {

    grupoDAO elgrupito = new grupoDAO();

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
                case 0:
                    out.println(listargr());
                    break;
                case 1:
                    long idgrupo = Long.parseLong(request.getParameter("idgrupo"));
                    long codigomateria = Long.parseLong(request.getParameter("lasmaterias"));
                    long idpersona = Long.parseLong(request.getParameter("losprofesores"));
                    String nombregrupo = request.getParameter("nombregrupo");
                    String descripcion = request.getParameter("ladescripcionxd");
                    response.sendRedirect("Vortal/guest_grupos/Registrar.jsp?confir=" + registrargrupo(idgrupo, codigomateria, idpersona, nombregrupo, descripcion));
                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    //out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String grupoBuscar = (String) request.getParameter("nombre");
                    out.println(consultaGrupo(grupoBuscar, opcionconsulta));

                    break;
                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        long serRec = Long.parseLong(request.getParameter("idgrupo"));
                        grupo recVO = new grupo();
                        recVO = elgrupito.consultaIndividualGrupo(serRec); //lamateriaxd.consultaIndividualMateria(serRec);
                        session.setAttribute("grupoChange", recVO);
                        out.println(grupoAModificar(recVO));

                    }
                    if (opcionmodificar == 2) {
                        grupo recVO = new grupo();
                        recVO = (grupo) session.getAttribute("grupoChange");
                        recVO.setCodigo_materia(Long.parseLong(request.getParameter("materia")));
                        recVO.setId_persona(Long.parseLong(request.getParameter("persona")));
                        recVO.setNombre_grupo(request.getParameter("nombregrupo"));
                        recVO.setDescripccion(request.getParameter("descripciongr"));
                        boolean respuestaModificar = (boolean) elgrupito.modificarGrupo(recVO);
                        if (respuestaModificar) {
                            session.removeAttribute("cursoChange");
                            response.sendRedirect("Vortal/guest_grupos/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_grupos/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    long elim = Long.parseLong(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarGrupo(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_grupos/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_grupos/Eliminar.jsp?confir=error");
                    }
                    break;

                case 6:
                    //listar profesores
                    out.print(losprofesores());
                    break;

                case 7://Listar materias
                    out.print(lasmaterias());
                    break;

            }
        } finally {
            out.close();
        }
    }

    private boolean eliminarGrupo(long idgrupo) {
        return elgrupito.eliminarGrupo(idgrupo);

    }

    private String losprofesores() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = elgrupito.listarprofeores();
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

    private String lasmaterias() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = elgrupito.listarmaterias();
        if (!datos.isEmpty()) {
            materiaVO tidoVO = new materiaVO();
            for (Object dato : datos) {
                tidoVO = (materiaVO) dato;
                opcion += "<option value=\"" + tidoVO.getcodigo_materia() + "\">" + tidoVO.getnombre_materia() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String registrargrupo(long id_grupo, long codigo_materia, long id_persona, String nombre_grupo, String descripccion) throws SQLException {

        boolean registro = false;

        String message = "";
        registro = (boolean) elgrupito.registrargrupo(id_grupo, codigo_materia, id_persona, nombre_grupo, descripccion);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listargr() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = elgrupito.listargrupos();
        if (!datos.isEmpty()) {
            grupo grupoVO = new grupo();
            for (Object dato : datos) {
                grupoVO = (grupo) dato;
                listado += "<tr>"
                        + "<td>" + grupoVO.getId_grupo() + "</td>"
                        + "<td>" + grupoVO.getNombre_grupo() + "</td>"
                        + "<td>" + grupoVO.getCodigo_materia() + "</td>"
                        + "<td>" + grupoVO.getId_persona() + "</td>"
                        + "<td>" + grupoVO.getDescripccion() + "</td>"
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

    private String consultaGrupo(String nombre, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = elgrupito.consultarGrupo(nombre);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarGrupo\" name=\"eliminarGrupo\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarGrupo\" name=\"modificarGrupo\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "codigo del grupo"
                + "</th>"
                + "<th>"
                + "Nombre del grupo"
                + "</th>"
                + "<th>"
                + "Descripcion del grupo"
                + "</th>"
                + "<th>"
                + "Profesor a Cargo"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                grupo rlVO = new grupo();
                rlVO = (grupo) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getId_grupo() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"idgrupo\" type=\"radio\" name=\"idgrupo\" value=\"" + rlVO.getId_grupo() + "\" onchange='javascript: grupoAModificar(" + rlVO.getId_grupo() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getId_grupo() + "</td>"
                        + "<td>" + rlVO.getNombre_grupo() + "</td>"
                        + "<td>" + rlVO.getDescripccion() + "</td>"
                        + "<td>" + rlVO.getId_persona() + "</td>"
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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarGrupo();\">Eliminar Grupo</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionGrupoModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String grupoAModificar(grupo rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"grupoModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"grupoModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>codigo del grupo</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idgrupo\" name=\"idgrupo\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_grupo() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Materia Vista por el grupo</label>"
                + " <select class=\"form-control\" name=\"materia\" id=\"materia\"> ";
        LinkedList xd = new LinkedList();
        xd = elgrupito.listarmaterias();
        if (!xd.isEmpty()) {
            materiaVO persVO = new materiaVO();
            for (Object xd1 : xd) {
                persVO = (materiaVO) xd1;
                if (persVO.getcodigo_materia() == rlVO.getCodigo_materia()) {
                    formulario += "<option value=\"" + persVO.getcodigo_materia() + "\" selected>" + persVO.getnombre_materia() + "</option>";
                } else {
                    formulario += "<option value=\"" + persVO.getcodigo_materia() + "\">" + persVO.getnombre_materia() + "</option>";
                }
            }
        }
        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nombre del Grupo</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"nombregrupo\" name=\"nombregrupo\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getNombre_grupo() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Id del Profesor a cargo</label>"
                + " <select class=\"form-control\" name=\"persona\" id=\"persona\"> ";
        LinkedList xd2 = new LinkedList();
        xd2 = elgrupito.listarprofeores();
        if (!xd.isEmpty()) {
            personasVO profVO = new personasVO();
            for (Object xd3 : xd2) {
                profVO = (personasVO) xd3;
                if (profVO.getid_persona() == rlVO.getId_persona()) {
                    formulario += "<option value=\"" + profVO.getid_persona() + "\" selected>" + profVO.getid_persona() + "</option>";
                } else {
                    formulario += "<option value=\"" + profVO.getid_persona() + "\">" + profVO.getid_persona() + "</option>";
                }
            }
        }
        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Descripcion de la materia</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"descripciongr\" name=\"descripciongr\" placeholder=\"Digita la descripcion\" value=\"" + rlVO.getDescripccion() + "\" required>"
                + "</div>"
                + "</form>";
        return formulario;
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
            Logger.getLogger(controllerGrupo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerGrupo.class.getName()).log(Level.SEVERE, null, ex);
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
