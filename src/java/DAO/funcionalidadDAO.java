/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.funcionalidadVO;
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
public class funcionalidadDAO {

    public funcionalidadDAO() {
    }

    public LinkedList consultaFuncionalidadesRol(int idrol) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("select f.id_funcionalidad, f.nombre_funcionalidad from funcionalidad as f, rol as r, funcionalidaddelrol as rf where rf.id_funcionalidad=f.id_funcionalidad and rf.id_rol=r.id_rol and r.id_rol=?;");
            select.setInt(1, idrol);
            rs = select.executeQuery();
            while (rs.next()) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                datos.add(funcVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList listarFuncionalidad() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * FROM funcionalidad ORDER BY id_funcionalidad ASC;");
            rs = select.executeQuery();
            while (rs.next()) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                funcVO.setdescripcion_funcionalidad(rs.getString("descripcion_funcionalidad"));
                funcVO.setlink_funcionalidad(rs.getString("link_funcionalidad"));
                datos.add(funcVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarFuncionalidadRol(int idrol, int idfunc) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO funcionalidaddelrol(id_rol, id_funcionalidad) VALUES (?, ?);");
            insert.setInt(1, idrol);
            insert.setInt(2, idfunc);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public boolean eliminarFuncionalidadRol(int idrol, int idfunc) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM funcionalidaddelrol  WHERE id_rol=? and id_funcionalidad=?;");
            delete.setInt(1, idrol);
            delete.setInt(2, idfunc);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList consultarFuncionalidad(String funcnombre) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM funcionalidad WHERE nombre_funcionalidad ~* ? ");
            select.setString(1, funcnombre);
            rs = select.executeQuery();
            while (rs.next()) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                funcVO.setdescripcion_funcionalidad(rs.getString("descripcion_funcionalidad"));
                funcVO.setlink_funcionalidad(rs.getString("link_funcionalidad"));
                datos.add(funcVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public funcionalidadVO consultaIndividualFuncionalidad(int funcid) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            funcionalidadVO funcVO = new funcionalidadVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM funcionalidad WHERE id_funcionalidad = ? ");
            select.setInt(1, funcid);
            rs = select.executeQuery();
            if (rs.next()) {
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                funcVO.setdescripcion_funcionalidad(rs.getString("descripcion_funcionalidad"));
                funcVO.setlink_funcionalidad(rs.getString("link_funcionalidad"));
            }
            return funcVO;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean eliminarFuncionalidad(int id) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM funcionalidad WHERE id_funcionalidad = ?");
            delete.setInt(1, id);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public boolean modificarFuncionalidad(funcionalidadVO funcVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE funcionalidad SET nombre_funcionalidad=?, descripcion_funcionalidad=?, link_funcionalidad=? WHERE id_funcionalidad = ?");
            update.setString(1, funcVO.getnombre_funcionalidad());
            update.setString(2, funcVO.getdescripcion_funcionalidad());
            update.setString(3, funcVO.getlink_funcionalidad());
            update.setInt(4, funcVO.getid_funcionalidad());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public boolean registrarFuncionalidad(String name, String description, String url) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO funcionalidad(funcnombre, funcdescripcion, funcurl) VALUES (?, ?, ?);");
            insert.setString(1, name);
            insert.setString(2, description);
            insert.setString(3, url);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(funcionalidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }
}
