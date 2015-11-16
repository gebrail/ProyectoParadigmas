package Controllers;

import DAO.personarolDAO;
import DAO.personasDAO;
import DAO.rolDAO;
import VO.Cuentas;
import VO.funcionalidadVO;
import VO.personarolVO;
import VO.personasVO;
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
public class ControllerPersonarol extends HttpServlet {

    private personarolDAO lapersonarol = new personarolDAO();
    personarolDAO lapersonaro2 = new personarolDAO();
    rolDAO tiporol = new rolDAO();
    personasDAO laspersonas = new personasDAO();

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
                    String username = request.getParameter("usuario");
                    String password = request.getParameter("password");
                    personarolVO personaO = validar(username, password);
                    if (personaO == null) {
                        response.sendRedirect("Vortal/index.jsp?error=Usuario o contrasena incorrecta");
                    } else {
                        session.setAttribute("menu", menu(personaO));
                        session.setAttribute("personaxd", nombredelapersona(personaO));
                        session.setAttribute("valido", true);
                        session.setAttribute("usuario", personaO); //Para obtener atrributos de la persona que este en sesion.
                        response.sendRedirect("Vortal/jsp/principal.jsp");
                    }
                    break;
                case 1:

                    String usern = request.getParameter("username");
                    String contra = request.getParameter("password");
                    long idpersona = Long.parseLong(request.getParameter("losdoc"));
                    int tiporol = Integer.parseInt(request.getParameter("losroles"));
                    response.sendRedirect("Vortal/guest_cuentas/Registrar.jsp?confir=" + crearcuenta(usern, contra, idpersona, tiporol));
//                 //               

                    break;
                case 2://mostrar cuentas por orden acendente del username Listar
                    out.println(listadodecuentas());
                    break;
                case 3:
                    int opcionconsulta = Integer.parseInt(request.getParameter("opcon"));
                    String cuentaBuscar = (String) request.getParameter("atributo");
                    out.println(consultaCuenta(cuentaBuscar, opcionconsulta));
                    break;
                case 4:
                    //modificar
                    int opcionmodificar = Integer.parseInt(request.getParameter("opmod"));
                    if (opcionmodificar == 1) {
                        String serRec = request.getParameter("idcuenta");
                        personarolVO recVO = new personarolVO();
                        recVO = lapersonaro2.consultaIndividualCuenta(serRec);
                        session.setAttribute("cuentaChange", recVO);
                        out.println(cuentaAModificar(recVO));

                    }
                    if (opcionmodificar == 2) {
                        personarolVO recVO = new personarolVO();
                        recVO = (personarolVO) session.getAttribute("cuentaChange");

                        recVO.setpassword(request.getParameter("passwordxd"));
                        recVO.setid_persona(Long.parseLong(request.getParameter("tipoper")));
                        recVO.setid_rol(Integer.parseInt(request.getParameter("tiporol")));

                        boolean respuestaModificar = (boolean) lapersonaro2.modificarCuenta(recVO);
                        if (respuestaModificar) {
                            session.removeAttribute("cuentaChange");
                            response.sendRedirect("Vortal/guest_cuentas/Modificar.jsp?confir=modificado");
                        } else {
                            response.sendRedirect("Vortal/guest_cuentas/Modificar.jsp?confir=error");
                        }
                    }
                    break;
                case 5://Eliminar
                    String elim = request.getParameter("eliminar");
                    boolean respuestaEliminar = eliminarCuenta(elim);
                    if (respuestaEliminar) {
                        session.removeAttribute("consulta");
                        response.sendRedirect("Vortal/guest_cuentas/Eliminar.jsp?confir=eliminado");
                    } else {
                        response.sendRedirect("Vortal/guest_cuentas/Eliminar.jsp?confir=error");
                    }
                    break;
            }
        } finally {
            out.close();
        }
    }

    private boolean eliminarCuenta(String elusername) {
        return lapersonaro2.eliminarCuenta(elusername);

    }

    //en esta funcion hago una validacion de datos que me envien y los compara con una consulta
    private personarolVO validar(String nombre, String contrasena) throws SQLException {
        personarolVO lapersonarolvo = lapersonarol.validarPersona(nombre, contrasena);
        return lapersonarolvo;
    }

    //se diseña el menu de toda la pagina a nivel dinamico
    private String menu(personarolVO lapersonarolvo) throws SQLException {
        LinkedList datos = new LinkedList();
        datos = lapersonarol.menu(lapersonarolvo);
        String menu = "";
        if (!datos.isEmpty()) {
            for (Object dato : datos) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO = (funcionalidadVO) dato;
                menu += " <li class=\"sub-menu dcjq-parent-li\"><a  href=\"../" + funcVO.getlink_funcionalidad() + "\"><i class=\"" + funcVO.geticono_funcionalidad() + "\"></i> " + "<span>" + funcVO.getnombre_funcionalidad() + "</span>" + "</a></li>\n";
            }

        } else {
            menu = "No tiene asignadas funcionalidades";
        }

        return menu;
    }

    private String nombredelapersona(personarolVO lapersonarolvo) throws SQLException, IOException {

        LinkedList datos = new LinkedList();
        datos = lapersonarol.nombresyapellidos(lapersonarolvo);

        String nombrexd = "";
        if (!datos.isEmpty()) {
            for (Object dato : datos) {
                personasVO personsVO = new personasVO();
                personsVO = (personasVO) dato;
                nombrexd
                        //     += " <p class=\"centered\"><a href=\"#\"><img src=\"" + personsVO.getfoto_persona() + "  \"    class=\"img-circle\" width=\"60\"></a></p>"

                        += " <p class=\"centered\"><a href=\"#\"><img src=\"https://chirimoyo.ac.uma.es/oleagen/imagenes/no-image.png\"    class=\"img-circle\" width=\"60\"></a></p>"
                        + "<h5 class=\"centered\">" + personsVO.getsegundonombre_persona() + "  " + personsVO.getprimerapellido_persona() + "</h5>";

            }

        } else {
            nombrexd = "pailas";
        }

        return nombrexd;
    }

    private String crearcuenta(String username, String password, long idpersona, int idrol) throws SQLException {
        boolean registro = false;
        String message = "";
        registro = (boolean) lapersonarol.registrarCuenta(username, password, idpersona, idrol);
        if (registro) {
            message = "registrado";
        } else {
            message = "error";

        }
        return message;
    }

    private String listadodecuentas() throws SQLException {
        LinkedList datos = new LinkedList();
        String listado = "";
        datos = lapersonarol.listarcuentas();//personaAO.listarpersonas();
        if (!datos.isEmpty()) {
            Cuentas cuentaVO = new Cuentas();
            for (Object dato : datos) {
                cuentaVO = (Cuentas) dato;

                listado += "<tr>"
                        + "<td>" + cuentaVO.getusername() + "</td>"
                        //  + "<td>" + cuentaVO.getpassword() + "</td>"
                        + "<td>" + cuentaVO.getid_persona() + "</td>"
                        + "<td>" + cuentaVO.getid_rol() + "</td>"
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

    private String consultaCuenta(String nombreusername, int opcionconsulta) throws SQLException {
        String consulta = "<br>";
        LinkedList datos = new LinkedList();
        datos = lapersonaro2.consultarCuenta(nombreusername);
        if (opcionconsulta == 1) {
            consulta += "<form role=\"form\" method=\"post\" id=\"eliminarCuenta\" name=\"eliminarCuenta\" enctype=\"application/x-www-form-urlencoded\">"
                    + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"5\"/>";
        } else if (opcionconsulta == 2) {
            consulta += "<form role=\"form\" id=\"modificarCuenta\" name=\"modificarCuenta\">";
        }
        consulta += "<div class=\"table-responsive\">"
                + "<table class=\"table\">"
                + "<thead>"
                + "<tr>"
                + "<th>"
                + "</th>"
                + "<th>"
                + "Rol"
                + "</th>"
                + "<th>"
                + "Username"
                + "</th>"
                + "<th>"
                + " Id de la persona"
                + "</th>"
                + "</tr>"
                + "</thead>"
                + "<tbody>";
        if (!datos.isEmpty()) {
            for (int i = 0; i < datos.size(); i++) {
                personarolVO recVO = new personarolVO();
                recVO = (personarolVO) datos.get(i);
                consulta += "<tr>";
                if (opcionconsulta == 1) {
                    consulta += "<td><input type=\"radio\" id=\"eliminar\" name=\"eliminar\" value=\"" + recVO.getusername() + "\">" + "</td>";
                } else if (opcionconsulta == 2) {
                    consulta += "<td><input type=\"radio\" id=\"idcuenta\" name=\"idcuenta\" value=\"" + recVO.getusername() + "\" onchange=\"cuentaAModificar('" + recVO.getusername() + "',4,1);\">" + "</td>";
                }

                LinkedList datos1 = new LinkedList();
                datos1 = tiporol.listarroles();
                if (!datos1.isEmpty()) {
                    rolVO tiroVO = new rolVO();
                    for (int j = 0; j < datos1.size(); j++) {
                        tiroVO = (rolVO) datos1.get(j);
                        if (tiroVO.getid_rol() == recVO.getid_rol()) {
                            consulta += "<td>" + tiroVO.getnombre_rol() + "</td>";
                        }
                    }
                }
                consulta += "<td>" + recVO.getusername() + "</td>"
                        + "<td>" + recVO.getid_persona() + "</td>"
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
            consulta += "<button type=\"button\" class=\"btn btn-danger\" onclick=\"confirmarEliminarCuenta();\">Eliminar Cuenta</button>";
        } else if (opcionconsulta == 2) {
            consulta += "<button type=\"button\" class=\"btn btn-primary\" onclick=\"SeleccionCuentaModificar();\" name=\"btnVisualizar\" id=\"btnVisualizar\">Visualizar</button>";
        }
        return consulta;
    }

    private String cuentaAModificar(personarolVO rlVO) {
        String formulario = "<form role=\"form\" method=\"post\" id=\"cuentaModificar\" enctype=\"application/x-www-form-urlencoded\" name=\"cuentaModificar\">"
                + "<input type=\"hidden\" id=\"opcion\" name=\"opcion\" value=\"4\"/>"
                + "<input type=\"hidden\" id=\"opmod\" name=\"opmod\" value=\"2\"/>"
                + "<div class=\"form-group\">"
                + "<label>Username</label>"
                + "<input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\" placeholder=\"Digita el nuevo username \" value=\"" + rlVO.getusername() + "\" disabled>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Password</label>"
                + "<input type=\"password\" class=\"form-control\" id=\"passwordxd\" name=\"passwordxd\" placeholder=\"Digita la nueva contraseña \" value=\"" + rlVO.getpassword() + "\" required>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Documento de la persona A cargo</label>"
                + " <select class=\"form-control\" name=\"tipoper\" id=\"tipoper\"> ";
        LinkedList xd = new LinkedList();
        xd = laspersonas.listardocumentos();
        if (!xd.isEmpty()) {
            personasVO persVO = new personasVO();
            for (Object xd1 : xd) {
                persVO = (personasVO) xd1;
                if (persVO.getid_persona() == rlVO.getid_persona()) {
                    formulario += "<option value=\"" + persVO.getid_persona() + "\" selected>" + persVO.getid_persona() + "</option>";
                } else {
                    formulario += "<option value=\"" + persVO.getid_persona() + "\">" + persVO.getid_persona() + "</option>";
                }
            }
        }
        formulario += "</select>"
                + "</div>"
                + "<div class=\"form-group\">"
                + "<label>Tipo Rol</label>"
                + " <select class=\"form-control\" name=\"tiporol\" id=\"tiporol\"> ";

        LinkedList datos = new LinkedList();
        datos = tiporol.listarroles();
        if (!datos.isEmpty()) {
            rolVO tireVO = new rolVO();
            for (int i = 0; i < datos.size(); i++) {
                tireVO = (rolVO) datos.get(i);
                if (tireVO.getid_rol() == rlVO.getid_rol()) {
                    formulario += "<option value=\"" + tireVO.getid_rol() + "\" selected>" + tireVO.getnombre_rol() + "</option>";
                } else {
                    formulario += "<option value=\"" + tireVO.getid_rol() + "\">" + tireVO.getnombre_rol() + "</option>";
                }
            }
        }
        formulario += "</select>"
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
            Logger.getLogger(ControllerPersonarol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControllerPersonarol.class.getName()).log(Level.SEVERE, null, ex);
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
