/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.aulaDAO;
import VO.aulaVO;
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
public class controllerAula extends HttpServlet {

    aulaDAO laaulaxd = new aulaDAO();

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
                    out.println(listadodeaulas());
                    break;
                case 1:
                    long aula = Long.parseLong(request.getParameter("idaula"));
                    String nombreaula = request.getParameter("nombreaula");
                    long tipaula = Long.parseLong(request.getParameter("tipoaula"));
                    response.sendRedirect("Vortal/guest_aula/Registrar.jsp?confir=" + crearaula(aula, nombreaula, tipaula));

                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    //out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String aulaBuscar = (String) request.getParameter("nombre");
                    out.println(consultaMateria(aulaBuscar, opcionconsulta));

                    break;
                case 4:
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        long serRec = Long.parseLong(request.getParameter("idaula"));
                        aulaVO recVO = new aulaVO();
                        recVO = laaulaxd.consultaIndividualAula(serRec);
                        session.setAttribute("aulaChange", recVO);
                        out.println(aulaAModificar(recVO));

                    }
                    if (opcionmodificar == 2) {
                        aulaVO recVO = new aulaVO();
                        recVO = (aulaVO) session.getAttribute("aulaChange");
                        recVO.setNombre_aula(request.getParameter("nombreaula"));
                        recVO.setId_tipoaula(Long.parseLong(request.getParameter("tipoaula")));
                        boolean respuestaModificar = (boolean) laaulaxd.modificarAula(recVO);
                        if (respuestaModificar) {
                            session.removeAttribute("aulaChange");
                            response.sendRedirect("Vortal/guest_aula/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_aula/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    long elim = Long.parseLong(request.getParameter("eliminar"));
                   boolean respuestaEliminar = eliminarAula(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_aula/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_aula/Eliminar.jsp?confir=error");
                    }
                    break;
                case 7:
                    out.println(tiposdeaulas());
                    break;
            }
        } finally {
            out.close();
        }
    }

    private String tiposdeaulas() throws SQLException {
        String opcion = "";
        LinkedList datos = new LinkedList();
        datos = laaulaxd.listarlostiposdeaula();
        if (!datos.isEmpty()) {
            tipoaulaVO tidoVO = new tipoaulaVO();
            for (Object dato : datos) {
                tidoVO = (tipoaulaVO) dato;
                opcion += "<option value=\"" + tidoVO.getId_tipoaula() + "\">" + tidoVO.getNombre_tipoaula() + "</option>";
            }
        } else {
            opcion += "<option>No hay Datos Error!</option>";
        }
        return opcion;
    }

    private boolean eliminarAula(long idaula) {
   return laaulaxd.eliminarAula(idaula);

    }
    private String crearaula(long idaula, String nombreaula, long tipaula) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) laaulaxd.registrarAula(idaula, nombreaula, tipaula);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listadodeaulas() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        String x = "";
        datos = laaulaxd.listaraulas();
        if (!datos.isEmpty()) {
            aulaVO curVO = new aulaVO();
            for (Object dato : datos) {
                curVO = (aulaVO) dato;
                LinkedList xd = new LinkedList();
                xd = laaulaxd.listarlostiposdeaula();
                if (!xd.isEmpty()) {
                    tipoaulaVO persVO = new tipoaulaVO();
                    for (Object xd1 : xd) {
                        persVO = (tipoaulaVO) xd1;
                        if (persVO.getId_tipoaula() == curVO.getId_tipoaula()) {
                            listado += "<tr>"
                                    + "<td>" + curVO.getId_aula() + "</td>"
                                    + "<td>" + curVO.getNombre_aula() + "</td>"
                                    + "<td>" + persVO.getNombre_tipoaula() + "</td>"
                                    + "</tr>";
                        }
                    }
                }

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
        datos = laaulaxd.consultarAula(nombre);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarAula\" name=\"eliminarAula\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarAula\" name=\"modificarAula\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "codigo del aula"
                + "</th>"
                + "<th>"
                + "Nombre del aula"
                + "</th>"
                + "<th>"
                + "Tipo de aula"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                aulaVO rlVO = new aulaVO();
                rlVO = (aulaVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + rlVO.getId_aula() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input id=\"idaula\" type=\"radio\" name=\"idaula\" value=\"" + rlVO.getId_aula() + "\" onchange='javascript: aulaAModificar(" + rlVO.getId_aula() + ",4,1);'>" + "</td>";
                }

                LinkedList xd = new LinkedList();
                xd = laaulaxd.listarlostiposdeaula();
                if (!xd.isEmpty()) {
                    tipoaulaVO persVO = new tipoaulaVO();
                    for (Object xd1 : xd) {
                        persVO = (tipoaulaVO) xd1;
                        if (persVO.getId_tipoaula() == rlVO.getId_tipoaula()) {
                            consulta += "<td>" + rlVO.getId_aula() + "</td>"
                                    + "<td>" + rlVO.getNombre_aula() + "</td>"
                                    + "<td>" + persVO.getNombre_tipoaula() + "</td>"
                                    + "</tr>";
                        }
                    }
                }

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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarSalon();\">Eliminar Aula</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionAulaModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String aulaAModificar(aulaVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"aulaModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"aulaModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>codigo del aula</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"idaulaxd\" name=\"idaulaxd\" placeholder=\"Digita el id nuevo\" value=\"" + rlVO.getId_aula() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Nombre del aula</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"nombreaula\" name=\"nombreaula\" placeholder=\"Digita el o modifica el nombre\" value=\"" + rlVO.getNombre_aula() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Id del Tipo del Salon</label>"
                + " <select class=\"form-control\" name=\"tipoaula\" id=\"tipoaula\"> ";
        LinkedList xd = new LinkedList();
        xd = laaulaxd.listarlostiposdeaula();
        if (!xd.isEmpty()) {
            tipoaulaVO persVO = new tipoaulaVO();
            for (Object xd1 : xd) {
                persVO = (tipoaulaVO) xd1;
                if (persVO.getId_tipoaula() == rlVO.getId_tipoaula()) {
                    formulario += "<option value=\"" + persVO.getId_tipoaula() + "\" selected>" + persVO.getNombre_tipoaula() + "</option>"
                            + "</div>"
                            + "</form>";
                } else {
                    formulario += "<option value=\"" + persVO.getId_tipoaula() + "\">" + persVO.getNombre_tipoaula() + "</option>"
                            + "</div>"
                            + "</form>";
                }
            }
        }

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
            Logger.getLogger(controllerAula.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controllerAula.class.getName()).log(Level.SEVERE, null, ex);
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
