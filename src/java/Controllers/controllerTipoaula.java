/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import DAO.tipodeaulaDAO;
import VO.tipoaulaVO;
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
public class controllerTipoaula extends HttpServlet {
 private tipodeaulaDAO tidoDAO = new tipodeaulaDAO();
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
                case 0://Listar
                    out.println(listadoAula());
                    break;
                case 1:
                    long idtipoaula= Long.parseLong(request.getParameter("idtipoaula"));
                    String nombre = (String) request.getParameter("nombre");
                    String descripcion = (String) request.getParameter("descripcion");
                    response.sendRedirect("Vortal/guest_tipoaula/Registrar.jsp?confir=" + registrarAula(idtipoaula,nombre, descripcion));
                    break;
                case 2:
                    out.println(opcionTipoAula());
                    break;
                case 3://Consulta
                    String nombreBuscar = (String) request.getParameter("nombre");
                    out.println(consultaTipoAula(nombreBuscar));
                    break;
                case 4://Eliminar
                    int elim = Integer.parseInt(request.getParameter("eliminar"));
                    boolean respuestaEliminar = eliminarAula(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_tipoaula/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_tipoaula/Eliminar.jsp?confir=error");
                    }
                    break;
            }

        } finally {
            out.close();
        }
    }

    private String opcionTipoAula() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = tidoDAO.listarTipoAula();
        if (!datos.isEmpty()) {
           tipoaulaVO tidoVO = new tipoaulaVO();
            for (int i = 0; i < datos.size(); i++) {
                tidoVO = (tipoaulaVO) datos.get(i);
                opcion += "<option value=\"" + tidoVO.getId_tipoaula()+ "\">" + tidoVO.getNombre_tipoaula()+ "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private String listadoAula() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        datos = tidoDAO.listarTipoAula();
        if (!datos.isEmpty()) {
           tipoaulaVO tidoVO = new tipoaulaVO();
            for (Object dato : datos) {
                tidoVO = (tipoaulaVO) dato;
                listado += "<tr>"
                        + "<td>" + tidoVO.getId_tipoaula()+ "</td>"
                        + "<td>" + tidoVO.getNombre_tipoaula() + "</td>"
                        + "<td>" + tidoVO.getDescripccion_aula()+ "</td>"
                        + "</tr>";
            }
        } else {
            listado += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        return listado;
    }

    private String registrarAula(long idtipoaula,String nombre, String descripcion) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) tidoDAO.registrarTipoAula(idtipoaula, nombre, descripcion);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";
        }
        return message;
    }

    private String consultaTipoAula(String nombretido) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = tidoDAO.consultarTipoAula(nombretido);
        consulta += "<form role=\"form\" method=\"post\" id=\"eliminarAula\" name=\"eliminarAula\" enctype=\"application/x-www-form-urlencoded\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>";
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
                tipoaulaVO tidoVO = new tipoaulaVO();
                tidoVO = (tipoaulaVO) datos.get(i);
                consulta += "<tr>";
                consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + tidoVO.getId_tipoaula() + "\">" + "</td>";
                consulta += "<td>" + tidoVO.getId_tipoaula() + "</td>"
                        + "<td>" + tidoVO.getNombre_tipoaula()+ "</td>"
                        + "<td>" + tidoVO.getDescripccion_aula()+ "</td>"
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
        consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarAula();\">Eliminar Registro</button>";
        return consulta;
    }

    private boolean eliminarAula(int idtipoaula) throws SQLException {
        return tidoDAO.eliminarTipoAula(idtipoaula);
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
         Logger.getLogger(controllerTipoaula.class.getName()).log(Level.SEVERE, null, ex);
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
         Logger.getLogger(controllerTipoaula.class.getName()).log(Level.SEVERE, null, ex);
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
