/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.rolVO;
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
public class rolDAO {

    public rolDAO() {

    }

    public LinkedList listarroles() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaroles = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("select * from rol;");
            rs = select.executeQuery();
            while (rs.next()) {
                rolVO rolxdVO = new rolVO();
                rolxdVO.setid_rol(rs.getInt("id_rol"));
                rolxdVO.setnombre_rol(rs.getString("nombre_rol"));
                rolxdVO.setdescripcion_rol(rs.getString("descripcion_rol"));
                listaroles.add(rolxdVO);
            }
            return listaroles;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listaroles;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarRol(int rolid, String name, String description) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO rol(id_rol,nombre_rol, descripcion_rol) VALUES (?, ?, ?);");
            insert.setInt(1, rolid);
            insert.setString(2, name);
            insert.setString(3, description);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarRol() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * FROM rol ORDER BY id_rol ASC;");
            rs = select.executeQuery();
            while (rs.next()) {
                rolVO rolVO = new rolVO();
                rolVO.setid_rol(rs.getInt("id_rol"));
                rolVO.setnombre_rol(rs.getString("nombre_rol"));
                rolVO.setdescripcion_rol(rs.getString("descripcion_rol"));
                datos.add(rolVO);
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

    public LinkedList consultarRol(String nombrerol) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM rol WHERE nombre_rol ~* ? ");
            select.setString(1, nombrerol);
            rs = select.executeQuery();
            while (rs.next()) {
                rolVO rolVO = new rolVO();
                rolVO.setid_rol(rs.getInt("id_rol"));
                rolVO.setnombre_rol(rs.getString("nombre_rol"));
                rolVO.setdescripcion_rol(rs.getString("descripcion_rol"));
                datos.add(rolVO);
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

    public rolVO consultaIndividualRol(int rolid) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            rolVO rlVO = new rolVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM rol WHERE id_rol = ? ");
            select.setInt(1, rolid);
            rs = select.executeQuery();
            if (rs.next()) {
                rlVO.setid_rol(rs.getInt("id_rol"));
                rlVO.setnombre_rol(rs.getString("nombre_rol"));
                rlVO.setdescripcion_rol(rs.getString("descripcion_rol"));
            }
            return rlVO;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean eliminarRol(int id) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM rol WHERE id_rol = ?");
            delete.setInt(1, id);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public boolean modificarRol(rolVO rlVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE rol SET nombre_rol=?, descripcion_rol=? WHERE id_rol = ?");
            update.setString(1, rlVO.getnombre_rol());
            update.setString(2, rlVO.getdescripcion_rol());
            update.setInt(3, rlVO.getid_rol());
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

    public LinkedList consultaRolPersona(String persDocumento) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("select r.id_rol, r.nombre_rol from personas as p, rol as r, personarol as pr where r.id_rol=pr.id_rol and pr.id_persona=p.id_persona and p.id_persona =?;");
            select.setString(1, persDocumento);
            rs = select.executeQuery();
            while (rs.next()) {
                rolVO rlVO = new rolVO();
                rlVO.setid_rol(rs.getInt("id_rol"));
                rlVO.setnombre_rol(rs.getString("nombre_rol"));
                datos.add(rlVO);
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

//    public boolean registrarPersonaRol(int idrol, String documento) {
//        conexionDB cn = null;
//        PreparedStatement insert = null;
//        try {
//            cn = new conexionDB();
//            boolean registro = false;
//            insert = cn.getConnection().prepareStatement("INSERT INTO personarol(persdocumento,rolid) VALUES (?, ?);");
//            insert.setString(1, documento);
//            insert.setInt(2, idrol);
//            int r = insert.executeUpdate();
//            if (r != 0) {
//                registro = true;
//            }
//            return registro;
//        } catch (SQLException ex) {
//            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return false;
//        } finally {
//            cn.desconectar();
//            cn.cerrarStatement(insert);
//        }
//    }
    public boolean eliminarPersonRol(int idrol, String documento) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM personarol WHERE id_rol=? and id_persona=?;");
            delete.setInt(1, idrol);
            delete.setString(2, documento);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(rolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

}
