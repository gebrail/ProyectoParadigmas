/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.aulaVO;
import VO.tipoaulaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilso
 */
public class aulaDAO {

    public void aula() {

    }

    public LinkedList listarlostiposdeaula() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaroles = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("select * from tipoaula;");
            rs = select.executeQuery();
            while (rs.next()) {

                //id_tipoaula, nombre_tipoaula, descripccion_aula
                tipoaulaVO cursosVO = new tipoaulaVO();
                cursosVO.setId_tipoaula(rs.getLong("id_tipoaula"));
                cursosVO.setNombre_tipoaula(rs.getString("nombre_tipoaula"));
                cursosVO.setDescripccion_aula(rs.getString("descripccion_aula"));
                listaroles.add(cursosVO);
            }
            return listaroles;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listaroles;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarAula(long idaula, String nombreaula, long tipaula) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO aula(id_aula, nombre_aula, id_tipoaula) VALUES (?, ?, ?);");
            insert.setLong(1, idaula);
            insert.setString(2, nombreaula);
            insert.setLong(3, tipaula);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(aulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listaraulas() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * FROM aula order by id_aula;");
            rs = select.executeQuery();
            while (rs.next()) {
                aulaVO matVO = new aulaVO();
                matVO.setId_aula(rs.getLong("id_aula"));
                matVO.setNombre_aula(rs.getString("nombre_aula"));
                matVO.setId_tipoaula(rs.getLong("id_tipoaula"));
                datos.add(matVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(aulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consultarAula(String elaula) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM aula WHERE nombre_aula~* ? ");
            select.setString(1, elaula);
            rs = select.executeQuery();
            while (rs.next()) {
                aulaVO aulaxdVO = new aulaVO();
                aulaxdVO.setId_aula(rs.getLong("id_aula"));
                aulaxdVO.setNombre_aula(rs.getString("nombre_aula"));
                aulaxdVO.setId_tipoaula(rs.getLong("id_tipoaula"));
                datos.add(aulaxdVO);

            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public aulaVO consultaIndividualAula(long idaula) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            aulaVO aulaxdVO = new aulaVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM aula WHERE id_aula = ? ");
            select.setLong(1, idaula);
            rs = select.executeQuery();
            if (rs.next()) {
                aulaxdVO.setId_aula(rs.getLong("id_aula"));
                aulaxdVO.setNombre_aula(rs.getString("nombre_aula"));
                aulaxdVO.setId_tipoaula(rs.getLong("id_tipoaula"));
            }
            return aulaxdVO;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarAula(aulaVO matVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE aula SET nombre_aula=?, id_tipoaula=? WHERE id_aula=?;");
            update.setString(1, matVO.getNombre_aula());
            update.setLong(2, matVO.getId_tipoaula());
            update.setLong(3, matVO.getId_aula());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(aulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }
    public boolean eliminarAula(long idAula) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM aula WHERE id_aula = ?");
            delete.setLong(1,idAula);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(aulaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

}
