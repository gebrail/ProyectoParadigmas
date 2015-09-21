/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.tipodedocumentoDAO;
import VO.tipodedocumentoVO;
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
public class controllerTipodocumento extends HttpServlet {

    private tipodedocumentoDAO tidoDAO = new tipodedocumentoDAO();

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
                    //       out.println(listadoTido());
                    break;
                case 1:
               //     String nombre = (String) request.getParameter("nombre");
                    //   String descripcion = (String) request.getParameter("descripcion");
                    // response.sendRedirect("gest_tdocumentos/registrar.jsp?confir=" + registrarTido(nombre, descripcion));
                    break;
                case 2:
                    out.println(opcionTipoDocumento());
                    break;
                case 3://Consulta
                    // String nombreBuscar = (String) request.getParameter("nombre");
                    // out.println(consultaTido(nombreBuscar));
                    break;
                case 4://Eliminar
                //int elim = Integer.parseInt(request.getParameter("eliminar"));
                //boolean respuestaEliminar = eliminarTido(elim);
                //if (respuestaEliminar) {
                //session.removeAttribute("consulta");
                //  response.sendRedirect("gest_tdocumentos/eliminar.jsp?confir=eliminado");
                //} else {
                //   response.sendRedirect("gest_tdocumentos/eliminar.jsp?confir=error");
                //}
                // break;
            }

        } finally {
            out.close();
        }
    }

    private String opcionTipoDocumento() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = tidoDAO.listarTipoDocumento();
        if (!datos.isEmpty()) {
            tipodedocumentoVO tidoVO = new tipodedocumentoVO();
            for (int i = 0; i < datos.size(); i++) {
                tidoVO = (tipodedocumentoVO) datos.get(i);
                opcion += "<option value=\"" + tidoVO.getid_documento() + "\">" + tidoVO.getnombre_documento() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos referentes a los tipos de documento!</option>";
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
            Logger.getLogger(controllerTipodocumento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerTipodocumento.class.getName()).log(Level.SEVERE, null, ex);
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
