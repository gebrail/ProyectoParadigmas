package DAO;

import Conexion.conexionDB;
import VO.funcionalidadVO;
import VO.personarolVO;
import VO.personasVO;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilson
 */
public class personarolDAO {

    public personarolDAO() {
    }

    public personarolVO validarPersona(String username, String password) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        personarolVO loginVO = null;
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personarol WHERE username=? AND password=?;");
            select.setString(1, username);
            select.setString(2, password);
            rs = select.executeQuery();
            if (rs.next()) {
                loginVO = new personarolVO();
                loginVO.setid_persona(rs.getInt("id_persona"));
                loginVO.setusername(rs.getString("username"));
                loginVO.setpassword(rs.getString("password"));

            }
            return loginVO;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

 // en esta funcion me traigo el link de las funcionalidades con su respectiva direccion :D   
    public LinkedList menu(personarolVO lapersonaVO) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            //uso la sentencia inner join para solo traerme los campos que esten en ambas tablas 1 sola vez
            select = cn.getConnection().prepareStatement("SELECT DISTINCT f.* FROM (funcionalidad AS f INNER JOIN(funcionalidaddelrol AS rf INNER JOIN personarol AS pr ON pr.id_rol = rf.id_rol)ON rf.id_funcionalidad = f.id_funcionalidad)WHERE pr.id_persona =?");
            select.setLong(1, lapersonaVO.getid_persona());
            rs = select.executeQuery();
            while (rs.next()) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                funcVO.setdescripcion_funcionalidad(rs.getString("descripcion_funcionalidad"));
                funcVO.setlink_funcionalidad(rs.getString("link_funcionalidad"));
                funcVO.seticono_funcionalidad(rs.getString("icono_funcionalidad"));
                datos.add(funcVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

   //en este metodo o funcion :p me traigo los nombres de la persona que este logeada
    
    public LinkedList nombresyapellidos(personarolVO elpersonaVO) throws IOException {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personas WHERE id_persona =? ");
            select.setLong(1, elpersonaVO.getid_persona());
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO personaxdVO = new personasVO();
                personaxdVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personaxdVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personaxdVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personaxdVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
                datos.add(personaxdVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

}
