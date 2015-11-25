/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.tipoaulaVO;
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
public class tipodeaulaDAO {

    public void tipodeaulaDAO() {

    }

    public LinkedList listarTipoAula() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaTipoAula = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM tipoaula");
            rs = select.executeQuery();
            while (rs.next()) {
                // id_tipoaula, nombre_tipoaula, descripccion_aula
                tipoaulaVO tidoVO = new tipoaulaVO();
                tidoVO.setId_tipoaula(rs.getInt("id_tipoaula"));
                tidoVO.setNombre_tipoaula(rs.getString("nombre_tipoaula"));
                tidoVO.setDescripccion_aula(rs.getString("descripccion_aula"));

                listaTipoAula.add(tidoVO);
            }
            return listaTipoAula;
        } catch (SQLException ex) {
            Logger.getLogger(tipodeaulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listaTipoAula;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarTipoAula(long idtipaula, String name, String description) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO tipoaula(id_tipoaula, nombre_tipoaula, descripccion_aula)VALUES (?, ?, ?);");
            insert.setLong(1, idtipaula);
            insert.setString(2, name);
            insert.setString(3, description);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(tipodeaulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }

    }

    public boolean eliminarTipoAula(int id) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM tipoaula WHERE id_tipoaula= ?");
            delete.setInt(1, id);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(tipodeaulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList consultarTipoAula(String nombretd) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();//        // id_tipoaula, nombre_tipoaula, descripccion_aula
            select = cn.getConnection().prepareStatement("SELECT * FROM tipoaula WHERE nombre_tipoaula ~* ? ");
            select.setString(1, nombretd);
            rs = select.executeQuery();
            while (rs.next()) {
                tipoaulaVO tdVO = new tipoaulaVO();
                tdVO.setId_tipoaula(rs.getLong("id_tipoaula"));
                tdVO.setNombre_tipoaula(rs.getString("nombre_tipoaula"));
                tdVO.setDescripccion_aula(rs.getString("descripccion_aula"));
                datos.add(tdVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(tipodeaulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

}
