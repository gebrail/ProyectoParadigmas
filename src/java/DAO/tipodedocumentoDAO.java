/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.tipodedocumentoVO;
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
public class tipodedocumentoDAO {

    public void tipodedocumentoDAO() {

    }

    public LinkedList listarTipoDocumento() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaTipoDocumento = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM tipodedocumento");
            rs = select.executeQuery();
            while (rs.next()) {
                tipodedocumentoVO tidoVO = new tipodedocumentoVO();
                tidoVO.setid_documento(rs.getInt("id_documento"));
                tidoVO.setnombre_documento(rs.getString("nombre_documento"));
                tidoVO.setdescripcion_documento(rs.getString("descripcion_documento"));

                listaTipoDocumento.add(tidoVO);
            }
            return listaTipoDocumento;
        } catch (SQLException ex) {
            Logger.getLogger(tipodedocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listaTipoDocumento;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarTipoDocumento(long iddocumento, String name, String description) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO tipodedocumento(id_documento, nombre_documento, descripcion_documento)VALUES (?, ?, ?);");
            insert.setLong(1, iddocumento);
            insert.setString(2, name);
            insert.setString(3, description);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(tipodedocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }

    }

    public boolean eliminarTipoDocumento(int id) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM tipodedocumento WHERE id_documento= ?");
            delete.setInt(1, id);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(tipodedocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList consultarTipoDocumento(String nombretd) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM tipodedocumento WHERE nombre_documento ~* ? ");
            select.setString(1, nombretd);
            rs = select.executeQuery();
            while (rs.next()) {
                tipodedocumentoVO tdVO = new tipodedocumentoVO();
                tdVO.setid_documento(rs.getLong("id_documento"));
                tdVO.setnombre_documento(rs.getString("nombre_documento"));
                tdVO.setdescripcion_documento(rs.getString("descripcion_documento"));
                datos.add(tdVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(tipodedocumentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

}
