package Controllers;

import DAO.personarolDAO;
import VO.funcionalidadVO;
import VO.personarolVO;
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
public class ControllerPersonarol extends HttpServlet {

   

    private personarolDAO lapersonarol = new personarolDAO();

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
                        response.sendRedirect("index.jsp?error=Usuario o contrasena incorrecta");
                    }else {
                        session.setAttribute("menu", menu(personaO));
                        session.setAttribute("personaxd", nombredelapersona(personaO));
                        session.setAttribute("valido", true);
                        session.setAttribute("usuario", personaO); //Para obtener atrributos de la persona que este en sesion.
                        response.sendRedirect("jsp/principal.jsp");
                    }
                    break;
                case 1:
                    
                    break;
                case 2:
                   
                    break;
                case 3:
            
                    break;
            }
        } finally {
            out.close();
        }
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
                nombrexd += " <h5 class=\"centered\">" + personsVO.getsegundonombre_persona() + "  " + personsVO.getprimerapellido_persona()+ "</h5>";
            }

        } else {
            nombrexd = "pailas";
        }

        return nombrexd;
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
