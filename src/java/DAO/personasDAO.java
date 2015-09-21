package DAO;

import Conexion.conexionDB;
import VO.personasVO;
import VO.tipodedocumentoVO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author wilson
 */
public class personasDAO {

    public personasDAO() {

    }

    public LinkedList listarpersonas() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT id_persona, id_documento, primernombre_persona, segundonombre_persona, \n"
                    + "       primerapellido_persona, segundoapellido_persona, foto_persona, \n"
                    + "       genero_persona, direccion_persona, telefono_persona, correo_persona, \n"
                    + "       estadocivil_persona,fechanacimiento_persona FROM personas ORDER BY id_persona ASC;");
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO personVO = new personasVO();
                personVO.setid_persona(rs.getLong("id_persona"));
                personVO.setid_documento(rs.getInt("id_documento"));
                personVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
                personVO.setfoto_persona(rs.getString("foto_persona"));
                personVO.setgenero_persona(rs.getString("genero_persona"));
                personVO.setdireccion_persona(rs.getString("direccion_persona"));
                personVO.settelefono_persona(rs.getLong("telefono_persona"));
                personVO.setcorreo_persona(rs.getString("correo_persona"));
                personVO.setestadocivil_persona(rs.getString("estadocivil_persona"));
                personVO.setfechanacimiento_persona(rs.getDate("fechanacimiento_persona"));
                datos.add(personVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarPersona(long idpersona, int tipodocumento, String primernombre, String segundonombre, String primerapellido, String segundoapellido, String genero, String direccion, long telefono, String correo, String estadocivil, Date fechanacimiento) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO personas(id_persona, id_documento, primernombre_persona, segundonombre_persona,primerapellido_persona, segundoapellido_persona, \n"
                    + "            genero_persona, direccion_persona, telefono_persona, correo_persona,estadocivil_persona,fechanacimiento_persona)\n"
                    + "    VALUES (?, ?, ?, ?, \n"
                    + "            ?, ?, \n"
                    + "            ?, ?, ?, ?, \n"
                    + "            ?,?);");

            insert.setLong(1, idpersona);
            insert.setInt(2, tipodocumento);
            insert.setString(3, primernombre);
            insert.setString(4, segundonombre);
            insert.setString(5, primerapellido);
            insert.setString(6, segundoapellido);
            insert.setString(7, genero);
            insert.setString(8, direccion);
            insert.setLong(9, telefono);
            insert.setString(10, correo);
            insert.setString(11, estadocivil);
            insert.setDate(12, fechanacimiento);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public boolean eliminarPersona(long id) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM personas WHERE id_persona = ?");
            delete.setLong(1, id);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList consultarPersona(String Apellido) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personas WHERE primerapellido_persona ~* ? ");
            select.setString(1, Apellido);
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO personVO = new personasVO();
                personVO.setid_persona(rs.getLong("id_persona"));
                personVO.setid_documento(rs.getInt("id_documento"));
                personVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
                personVO.setfoto_persona(rs.getString("foto_persona"));
                personVO.setgenero_persona(rs.getString("genero_persona"));
                personVO.setdireccion_persona(rs.getString("direccion_persona"));
                personVO.settelefono_persona(rs.getLong("telefono_persona"));
                personVO.setcorreo_persona(rs.getString("correo_persona"));
                personVO.setestadocivil_persona(rs.getString("estadocivil_persona"));
                personVO.setfechanacimiento_persona(rs.getDate("fechanacimiento_persona"));
                datos.add(personVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public personasVO consultaIndividualPersona(long idperson) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            personasVO personVO = new personasVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM personas WHERE id_persona = ? ");
            select.setLong(1, idperson);
            rs = select.executeQuery();
            if (rs.next()) {

                personVO.setid_persona(rs.getLong("id_persona"));
                personVO.setid_documento(rs.getInt("id_documento"));
                personVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
                personVO.setfoto_persona(rs.getString("foto_persona"));
                personVO.setgenero_persona(rs.getString("genero_persona"));
                personVO.setdireccion_persona(rs.getString("direccion_persona"));
                personVO.settelefono_persona(rs.getLong("telefono_persona"));
                personVO.setcorreo_persona(rs.getString("correo_persona"));
                personVO.setestadocivil_persona(rs.getString("estadocivil_persona"));
                personVO.setfechanacimiento_persona(rs.getDate("fechanacimiento_persona"));
            }
            return personVO;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList listartipodocumentos() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listatipodocumentos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM tipodedocumento");
            rs = select.executeQuery();
            while (rs.next()) {
                tipodedocumentoVO falVO = new tipodedocumentoVO();
                falVO.setid_documento(rs.getInt("id_documento"));
                falVO.setnombre_documento(rs.getString("nombre_documento"));
                falVO.setdescripcion_documento(rs.getString("descripcion_documento"));

            }
            return listatipodocumentos;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listatipodocumentos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarPersona(personasVO rlVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE personas SET id_documento=?, primernombre_persona=?, segundonombre_persona=?,primerapellido_persona=?, segundoapellido_persona=?, foto_persona=?,genero_persona=?, direccion_persona=?, telefono_persona=?, correo_persona=?,estadocivil_persona=?, fechanacimiento_persona=? WHERE id_persona=?");

            update.setInt(1, rlVO.getid_documento());
            update.setString(2, rlVO.getprimernombre_persona());
            update.setString(3, rlVO.getsegundonombre_persona());
            update.setString(4, rlVO.getprimerapellido_persona());
            update.setString(5, rlVO.getsegundoapellido_persona());
            update.setString(6, null);
            update.setString(7, rlVO.getgenero_persona());
            update.setString(8, rlVO.getdireccion_persona());
            update.setLong(9, rlVO.gettelefono_persona());
            update.setString(10, rlVO.getcorreo_persona());
            update.setString(11, rlVO.getestadocivil_persona());
            update.setDate(12, rlVO.getfechanacimiento_persona());
            update.setLong(13, rlVO.getid_persona());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }
}
