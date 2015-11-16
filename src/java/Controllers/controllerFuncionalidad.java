/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import DAO.funcionalidadDAO;
import VO.funcionalidadVO;
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
public class controllerFuncionalidad extends HttpServlet {

        private funcionalidadDAO funcDAO = new funcionalidadDAO();

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
                    int idrol = Integer.parseInt(request.getParameter("idrol"));
                    out.println(listadoFuncionalidadRol(idrol));
                    break;
                case 1:
                    int idrl = Integer.parseInt(request.getParameter("idrol"));
                    boolean op = Boolean.parseBoolean(request.getParameter("op"));
                    int idfun = Integer.parseInt(request.getParameter("idfunc"));
                    out.println(guardarCambios(idrl, op, idfun));
                    break;
                case 2:
                    String nombre = (String) request.getParameter("nombre");
                    String descripcion = (String) request.getParameter("descripcion");
                    String url = (String) request.getParameter("url");
                    response.sendRedirect("gest_funcionalidad/registrar.jsp?confir=" + registrarFuncionalidad(nombre, descripcion, url));
                    break;
                case 3: //Listado Funcionalidades
                    out.println(listadoFuncionalidad());
                    break;
            }
        } finally {
            out.close();
        }
    }

    private String guardarCambios(int idrol, boolean opcion, int idfun) throws SQLException {
        if (opcion) {
            funcDAO.registrarFuncionalidadRol(idrol, idfun);
        } else {
            funcDAO.eliminarFuncionalidadRol(idrol, idfun);
        }
        return listadoFuncionalidadRol(idrol);

    }

    private String listadoFuncionalidadRol(int idrol) throws SQLException {
        LinkedList datos1 = new LinkedList();
        LinkedList datos2 = new LinkedList();
        String listado = "<table class=\"table table-hover\">";
        datos1 = funcDAO.listarFuncionalidad();
        datos2 = funcDAO.consultaFuncionalidadesRol(idrol);
        if (!datos1.isEmpty()) {
            boolean ok = false;
            for (int i = 0; i < datos1.size(); i++) {
                funcionalidadVO func1 = new funcionalidadVO();
                func1 = (funcionalidadVO) datos1.get(i);
                for (int j = 0; j < datos2.size(); j++) {
                    funcionalidadVO func2 = new funcionalidadVO();
                    func2 = (funcionalidadVO) datos2.get(j);
                    if (func2.getid_funcionalidad()== func1.getid_funcionalidad()) {
                        listado += "<tr class=\"success\">"
                                + "<td class=\"text-center\">"
                                + func1.getnombre_funcionalidad()
                                + "</td>\n"
                                + "<td>\n"
                                + "<label>"
                                + "<input type=\"checkbox\" onchange=\"modificarRolFunc('" + func1.getid_funcionalidad() + "',this.checked," + idrol + ");\" checked > Activado"
                                + "</label>\n"
                                + "</td>\n"
                                + "</tr>";
                        ok = true;
                    }
                }
                if (!ok) {
                    listado += "<tr class=\"danger\">"
                            + "<td class=\"text-center\">"
                            + func1.getnombre_funcionalidad()
                            + "</td>\n"
                            + "<td>\n"
                            + "<label>"
                            + "<input type=\"checkbox\" onchange=\"modificarRolFunc('" + func1.getid_funcionalidad() + "',this.checked, " + idrol + ");\"> Desactivado"
                            + "</label>\n"
                            + "</td>\n"
                            + "</tr>";
                }
                ok = false;
            }
        }
        return listado;
    }

    private String registrarFuncionalidad(String nombre, String descripcion, String url) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) funcDAO.registrarFuncionalidad(nombre, descripcion, url);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";
        }
        return message;
    }

    private String listadoFuncionalidad() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        datos = funcDAO.listarFuncionalidad();
        if (!datos.isEmpty()) {
            funcionalidadVO funcVO = new funcionalidadVO();
            for (int i = 0; i < datos.size(); i++) {
                funcVO = (funcionalidadVO) datos.get(i);
                listado += "<tr>"
                        + "<td>" + funcVO.getid_funcionalidad() + "</td>"
                        + "<td>" + funcVO.getnombre_funcionalidad() + "</td>"
                        + "<td>" + funcVO.getdescripcion_funcionalidad() + "</td>"
                        + "<td>" + funcVO.getlink_funcionalidad() + "</td>"
                        + "</tr>";
            }
        } else {
            listado += "<tr>"
                    + "<td colspan=\"3\">" + "No se han encontrado registros." + "</td>"
                    + "</tr>";
        }
        return listado;
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
                Logger.getLogger(controllerFuncionalidad.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(controllerFuncionalidad.class.getName()).log(Level.SEVERE, null, ex);
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
