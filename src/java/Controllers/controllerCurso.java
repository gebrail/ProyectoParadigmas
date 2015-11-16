/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.cursoDAO;
import VO.cursoVO;
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
 * @author wilso
 */
public class controllerCurso extends HttpServlet {

    cursoDAO elcursoxd = new cursoDAO();

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
                    out.println(listadocursos());
                    break;
                case 1:
                    int idcurso = Integer.parseInt(request.getParameter("idcurso"));
                    String nombrecurso = request.getParameter("nombrecurso");
                    String descripcion = request.getParameter("descripcion");
                    response.sendRedirect("Vortal/guest_curso/Registrar.jsp?confir=" + crearcurso(idcurso, nombrecurso, descripcion));

                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    //out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String cursoBuscar = (String) request.getParameter("nombre");
                    out.println(consultaCurso(cursoBuscar, opcionconsulta));

                    break;
                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        int serRec = Integer.parseInt(request.getParameter("idcurso"));
                        cursoVO recVO = new cursoVO();
                        recVO = elcursoxd.consultaIndividualCurso(serRec);
                        session.setAttribute("cursoChange", recVO);
                        out.println(cursoAModificar(recVO));

                    }
                    if (opcionmodificar == 2) {
                        cursoVO recVO = new cursoVO();
                        recVO = (cursoVO) session.getAttribute("cursoChange");
                        recVO.setnombre_curso(request.getParameter("nombrecurso"));
                        recVO.setdescripcion_curso(request.getParameter("descripcioncurso"));
                        boolean respuestaModificar = (boolean) elcursoxd.modificarCurso(recVO);
                        if (respuestaModificar) {
                            session.removeAttribute("cursoChange");
                            response.sendRedirect("Vortal/guest_curso/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_curso/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    long elim = Long.parseLong(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarCurso(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_curso/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_curso/Eliminar.jsp?confir=error");
                    }
                    break;
            }
        } finally {
            out.close();
        }
    }

        private boolean eliminarCurso(long idcurso) {
        return elcursoxd.eliminarCurso(idcurso);

    }
    
    
    private String crearcurso(int idcurso, String nobmrecurso, String descripcion) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) elcursoxd.registrarCurso(idcurso, nobmrecurso, descripcion);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listadocursos() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = elcursoxd.listarcursos();
        if (!datos.isEmpty()) {
            cursoVO curVO = new cursoVO();
            for (Object dato : datos) {
                curVO = (cursoVO) dato;
                listado += "<tr>"
                        + "<td>" + curVO.getid_curso() + "</td>"
                        + "<td>" + curVO.getnombre_curso() + "</td>"
                        + "<td>" + curVO.getdescripcion_curso() + "</td>"
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

    private String consultaCurso(String nombre, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = elcursoxd.consultarCurso(nombre);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarCurso\" name=\"eliminarCurso\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarCurso\" name=\"modificarCurso\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Id_Curso"
                + "</th>"
                + "<th>"
                + "Nombre_Curso"
                + "</th>"
                + "<th>"
                + "Descripcion del Curso"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                cursoVO rlVO = new cursoVO();
                rlVO = (cursoVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getid_curso() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"idcurso\" type=\"radio\" name=\"idcurso\" value=\"" + rlVO.getid_curso() + "\" onchange='javascript: cursoAModificar(" + rlVO.getid_curso() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getid_curso() + "</td>"
                        + "<td>" + rlVO.getnombre_curso() + "</td>"
                        + "<td>" + rlVO.getdescripcion_curso() + "</td>"
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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarCurso();\">Eliminar Curso</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionCursoModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String cursoAModificar(cursoVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"cursoModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"cursoModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>ID Curso</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idcursoxd\" name=\"idcursoxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getid_curso() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nombre del curso</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"nombrecurso\" name=\"nombrecurso\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getnombre_curso() + "\" required>"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Descripcion del curso</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"descripcioncurso\" name=\"descripcioncurso\" placeholder=\"Digita la descripcion\" value=\"" + rlVO.getdescripcion_curso() + "\" required>"
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
            Logger.getLogger(controllerCurso.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerCurso.class.getName()).log(Level.SEVERE, null, ex);
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
