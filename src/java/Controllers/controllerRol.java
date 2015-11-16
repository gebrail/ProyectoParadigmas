/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.rolDAO;
import VO.rolVO;
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
public class controllerRol extends HttpServlet {

    private rolDAO rlDAO = new rolDAO();
    rolDAO rolAO = new rolDAO();

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
                    out.println(roles());
                    break;
                case 1://Registrar
                    int elidrol = Integer.parseInt(request.getParameter("idrolxd"));
                    String nombre = (String) request.getParameter("nombre");
                    String descripcion = (String) request.getParameter("descripcion");
                    response.sendRedirect("Vortal/guest_roles/Registrar.jsp?confir=" + registrarRol(elidrol, nombre, descripcion));
                    break;

                case 2://Consulta eliminar o modificar
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String nombreBuscar = (String) request.getParameter("nombre");
                    out.println(consultaRol(nombreBuscar, opcionconsulta));
                    break;

                case 3://Eliminar
                    int elim = Integer.parseInt(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarRol(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_roles/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_roles/Eliminar.jsp?confir=error");
                    }
                    break;

                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        int idrol = Integer.parseInt(request.getParameter("rlid"));
                        rolVO rlVO = new rolVO();
                        rlVO = rlDAO.consultaIndividualRol(idrol);
                        session.setAttribute("rolChange", rlVO);
                        out.println(rolAModificar(rlVO));
                    }
                    if (opcionmodificar == 2) {
                        rolVO rlVO = new rolVO();
                        rlVO = (rolVO) session.getAttribute("rolChange");
                        rlVO.setnombre_rol(request.getParameter("nombre2"));
                        rlVO.setdescripcion_rol(request.getParameter("descripcion"));
                        boolean respuestaModificar = (boolean) rlDAO.modificarRol(rlVO);
                        if (respuestaModificar) {
                            session.removeAttribute("rolChange");
                            response.sendRedirect("Vortal/guest_roles/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_roles/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5:
                    out.println(Roles());
                    break;
                case 6://Consulta Roles por Persona
                    int opcionasignar = Integer.parseInt(request.getParameter("opasig"));
                    if (opcionasignar == 1) {
                        String docu = request.getParameter("persDocumento");
                        out.println(listadoRolPersona(docu));
                    }
                    if (opcionasignar == 2) {
                        String documento = request.getParameter("documento");
                        String[] check = request.getParameterValues("checkbox");
                        guardarCambiosRoles(check, documento);
                        response.sendRedirect("Vortal/guest_personas/asignarrol.jsp?confir=cambiosGuardados");

                    }
                    break;
                     case 7://mostrar personas Listar
                    out.println(listadoRol());
                    break;
            }
        } finally {
            out.close();
        }
    }

    private String listadoRol() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        datos = rlDAO.listarRol();
        if (!datos.isEmpty()) {
            rolVO rolVO = new rolVO();
            for (int i = 0; i < datos.size(); i++) {
                rolVO = (rolVO) datos.get(i);
                listado += "<tr>"
                        + "<td>" + rolVO.getid_rol() + "</td>"
                        + "<td>" + rolVO.getnombre_rol() + "</td>"
                        + "<td>" + rolVO.getdescripcion_rol() + "</td>"
                        + "</tr>";
            }
        } else {
            listado += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        return listado;
    }

    private String Roles() throws SQLException {
        LinkedList datos = new LinkedList();
        String respuesta = "<div class=\"panel-group\" id=\"accordion\">";
        datos = rlDAO.listarRol();
        if (!datos.isEmpty()) {
            rolVO rolVO = new rolVO();
            for (int i = 0; i < datos.size(); i++) {
                rolVO = (rolVO) datos.get(i);
                respuesta += "<div class=\"panel panel-default\">"
                        + "<div class=\"panel-heading\">"
                        + "<h4 class=\"panel-title\">"
                        + "<a id=\"rl" + rolVO.getid_rol() + "\" onclick=\"verFunc(document.getElementById('rl" + rolVO.getid_rol() + "')," + rolVO.getid_rol() + ");\" href=\"#collapse" + rolVO.getid_rol() + "\">"
                        + rolVO.getnombre_rol()
                        + "</a>"
                        + "</h4>"
                        + "</div>"
                        + "<div id=\"collapse" + rolVO.getid_rol() + "\" class=\"panel-collapse collapse\">"
                        + "<div class=\"panel-body\" id=\"rol" + rolVO.getid_rol() + "\">"
                        + "</div>"
                        + "</div>"
                        + "</div>";
            }
        } else {
            System.out.println("Sin Datos Arreglar");
        }
        respuesta += "</div>";
        return respuesta;
    }

    private String registrarRol(int idrol, String nombre, String descripcion) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) rlDAO.registrarRol(idrol, nombre, descripcion);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";
        }
        return message;
    }

    private String consultaRol(String nombrerol, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = rlDAO.consultarRol(nombrerol);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarRol\" name=\"eliminarRol\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"3\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarRol\" name=\"modificarRol\">";
            //+ "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
            //+ "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"1\"/>";
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
                + "Nombre"
                + "</th>"
                + "<th>"
                + "Descripción"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                rolVO rlVO = new rolVO();
                rlVO = (rolVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getid_rol() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"rlid\" type=\"radio\" name=\"rlid\" value=\"" + rlVO.getid_rol() + "\" onchange='javascript: rolAModificar(" + rlVO.getid_rol() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getid_rol() + "</td>"
                        + "<td>" + rlVO.getnombre_rol() + "</td>"
                        + "<td>" + rlVO.getdescripcion_rol() + "</td>"
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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarRol();\">Eliminar Registro</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"seleccionRolModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private boolean eliminarRol(int idRol) throws SQLException {
        return rlDAO.eliminarRol(idRol);
    }

    private String rolAModificar(rolVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"rolModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"rolModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>Nombre</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"nombre2\" name=\"nombre2\" placeholder=\"Digita el nombre nuevo\" value=\"" + rlVO.getnombre_rol() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Descripcion</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"descripcion\" name=\"descripcion\" placeholder=\"Digita la nueva descripcion\" value=\"" + rlVO.getdescripcion_rol() + "\" required>"
                + "</div>"
                + "</form>";
        return formulario;
    }

    private String listadoRolPersona(String persDocumento) throws SQLException {
        LinkedList datos1 = new LinkedList();
        LinkedList datos2 = new LinkedList();
        String listado = "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"6\"/>"
                + "<input type=\"hidden\" id=\"opasig\" name=\"opasig\" value=\"2\"/>"
                + "<input type=\"hidden\" id=\"opasig\" name=\"documento\" value=\"" + persDocumento + "\"/>"
                + "<table class=\"table table-hover\">";
        datos1 = rlDAO.listarRol();
        datos2 = rlDAO.consultaRolPersona(persDocumento);
        if (!datos1.isEmpty()) {
            boolean ok = false;
            for (int i = 0; i < datos1.size(); i++) {
                rolVO rl1 = new rolVO();
                rl1 = (rolVO) datos1.get(i);
                for (int j = 0; j < datos2.size(); j++) {
                    rolVO rl2 = new rolVO();
                    rl2 = (rolVO) datos2.get(j);
                    if (rl2.getid_rol() == rl1.getid_rol()) {
                        listado += "<tr class=\"success\" id=\"" + i + "\">"
                                + "<td class=\"text-center\">"
                                + rl1.getnombre_rol()
                                + "</td>\n"
                                + "<td>\n"
                                + "<input type=\"checkbox\" name=\"checkbox\" id=\"check" + i + "\"  value=\"" + rl1.getid_rol() + "\" onchange=\"cambiarColor(" + i + ");\" checked > <label id=\"r" + i + "\">Activado</label>"
                                + "</td>\n"
                                + "</tr>";
                        ok = true;
                    }
                }
                if (!ok) {
                    listado += "<tr class=\"danger\" id=\"" + i + "\">"
                            + "<td class=\"text-center\">"
                            + rl1.getnombre_rol()
                            + "</td>\n"
                            + "<td>\n"
                            + "<input type=\"checkbox\" name=\"checkbox\" id=\"check" + i + "\" value=\"" + rl1.getid_rol() + "\" onchange=\"cambiarColor(" + i + ");\"> <label id=\"r" + i + "\">Desactivado</label>"
                            + "</td>\n"
                            + "</tr>";
                }
                ok = false;
            }
        }
        return listado;
    }

    private void guardarCambiosRoles(String[] check, String doc) {
        LinkedList datos = new LinkedList();
        LinkedList datos1 = new LinkedList();
        datos1 = rlDAO.listarRol();
        datos = rlDAO.consultaRolPersona(doc);
        boolean ok = false;
        for (int i = 0; i < datos1.size(); i++) {
            rolVO rlvo = (rolVO) datos1.get(i);
            int idrol = rlvo.getid_rol();
            if (ListadoViejo(idrol, datos)) {
                if (!ListadoNuevo(idrol, check)) {
                    rlDAO.eliminarPersonRol(idrol, doc);
                }
            } else {
                if (ListadoNuevo(idrol, check)) {
                    //rlDAO.registrarPersonaRol(idrol, doc);
                }
            }

        }
    }

    private boolean ListadoNuevo(int rlid, String[] check) {
        boolean ok = false;
        if (check != null) {
            for (int i = 0; i < check.length; i++) {
                int rolid = Integer.parseInt(check[i]);
                if (rlid == rolid) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    private boolean ListadoViejo(int rlid, LinkedList datos) {
        boolean ok = false;
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                rolVO rolid = new rolVO();
                rolid = (rolVO) datos.get(i);
                if (rlid == rolid.getid_rol()) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    private String roles() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = rolAO.listarroles();
        if (!datos.isEmpty()) {
            rolVO tidoVO = new rolVO();
            for (Object dato : datos) {
                tidoVO = (rolVO) dato;
                opcion += "<option value=\"" + tidoVO.getid_rol() + "\">" + tidoVO.getnombre_rol() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
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
            Logger.getLogger(controllerRol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerRol.class.getName()).log(Level.SEVERE, null, ex);
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
