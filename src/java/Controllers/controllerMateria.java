/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import DAO.materiaDAO;
import VO.cursoVO;
import VO.materiaVO;
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
public class controllerMateria extends HttpServlet {

    materiaDAO lamateriaxd = new materiaDAO();

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
                    out.println(listadomateria());
                    break;
                case 1:
                    int idmateria = Integer.parseInt(request.getParameter("idmateria"));
                    String nombremateria = request.getParameter("nombremateria");
                    String descripcion = request.getParameter("descripcion");
                    response.sendRedirect("Vortal/guest_materias/Registrar.jsp?confir=" + crearmateria(idmateria, nombremateria, descripcion));

                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    //out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String materiaBuscar = (String) request.getParameter("nombre");
                    out.println(consultaMateria(materiaBuscar, opcionconsulta));

                    break;
                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        long serRec = Long.parseLong(request.getParameter("idmateria"));
                        materiaVO recVO = new materiaVO();
                        recVO = lamateriaxd.consultaIndividualMateria(serRec);
                        session.setAttribute("materiaChange", recVO);
                        out.println(materiaAModificar(recVO));

                    }
                    if (opcionmodificar == 2) {
                        materiaVO recVO = new materiaVO();
                        recVO = (materiaVO) session.getAttribute("materiaChange");
                        recVO.setnombre_materia(request.getParameter("nombremateria"));
                        recVO.setdescripcion(request.getParameter("descripcionmateria"));
                        boolean respuestaModificar = (boolean) lamateriaxd.modificarMateria(recVO);
                        if (respuestaModificar) {
                            session.removeAttribute("cursoChange");
                            response.sendRedirect("Vortal/guest_materias/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_materias/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    long elim = Long.parseLong(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarMateria(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_materias/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_materias/Eliminar.jsp?confir=error");
                    }
                    break;
            }
        } finally {
            out.close();
        }
    }

         private boolean eliminarMateria(long idmateria) {
        return lamateriaxd.eliminarMateria(idmateria);

    }
    
    
    private String crearmateria(long idcurso, String nobmrecurso, String descripcion) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) lamateriaxd.registrarMateria(idcurso, nobmrecurso, descripcion);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listadomateria() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = lamateriaxd.listarmaterias();
        if (!datos.isEmpty()) {
            materiaVO curVO = new materiaVO();
            for (Object dato : datos) {
                curVO = (materiaVO) dato;
                listado += "<tr>"
                        + "<td>" + curVO.getcodigo_materia() + "</td>"
                        + "<td>" + curVO.getnombre_materia() + "</td>"
                        + "<td>" + curVO.getdescripcion() + "</td>"
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

    private String consultaMateria(String nombre, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos= lamateriaxd.consultarMateria(nombre);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarMateria\" name=\"eliminarMateria\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarMateria\" name=\"modificarMateria\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "codigo de la materia"
                + "</th>"
                + "<th>"
                + "Nombre de la materia"
                + "</th>"
                + "<th>"
                + "Descripcion de la materia"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                materiaVO rlVO = new materiaVO();
                rlVO = (materiaVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getcodigo_materia() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"idmateria\" type=\"radio\" name=\"idmateria\" value=\"" + rlVO.getcodigo_materia() + "\" onchange='javascript: materiaAModificar(" + rlVO.getcodigo_materia() + ",4,1);'>" + "</td>";
                }
                consulta += "<td>" + rlVO.getcodigo_materia() + "</td>"
                        + "<td>" + rlVO.getnombre_materia() + "</td>"
                        + "<td>" + rlVO.getdescripcion() + "</td>"
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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarMateria();\">Eliminar Materia</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionMateriaModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String materiaAModificar(materiaVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"materiaModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"materiaModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>codigo de la materia</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idcursoxd\" name=\"idmateriaxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getcodigo_materia() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nombre de  la materia</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"nombremateria\" name=\"nombremateria\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getnombre_materia() + "\" required>"
                + "</div>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Descripcion de la materia</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"descripcionmateria\" name=\"descripcionmateria\" placeholder=\"Digita la descripcion\" value=\"" + rlVO.getdescripcion() + "\" required>"
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
            Logger.getLogger(controllerMateria.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerMateria.class.getName()).log(Level.SEVERE, null, ex);
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
