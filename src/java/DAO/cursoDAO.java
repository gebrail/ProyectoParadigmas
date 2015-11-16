/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.cursoVO;
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
public class cursoDAO {

    public cursoDAO() {

    }

    public boolean registrarCurso(int idcurso, String nobmrecurso, String descripcion) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO curso( id_curso, nombre_curso, descripcion_curso)VALUES (?, ?, ?);");
            insert.setInt(1, idcurso);
            insert.setString(2, nobmrecurso);
            insert.setString(3, descripcion);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(cursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarcursos() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT id_curso, nombre_curso, descripcion_curso FROM curso order by id_curso asc;");
            rs = select.executeQuery();
            while (rs.next()) {
                cursoVO curVO = new cursoVO();
                curVO.setid_curso(rs.getInt("id_curso"));
                curVO.setnombre_curso(rs.getString("nombre_curso"));
                curVO.setdescripcion_curso(rs.getString("descripcion_curso"));
                datos.add(curVO);
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

    public LinkedList consultarCurso(String elcurso) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM curso WHERE nombre_curso~* ? ");
            select.setString(1, elcurso);
            rs = select.executeQuery();
            while (rs.next()) {
                cursoVO cursoxdVO = new cursoVO();
                cursoxdVO.setid_curso(rs.getInt("id_curso"));
                cursoxdVO.setnombre_curso(rs.getString("nombre_curso"));
                cursoxdVO.setdescripcion_curso(rs.getString("descripcion_curso"));
                datos.add(cursoxdVO);

            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(cursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public cursoVO consultaIndividualCurso(int idcurso) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            cursoVO curVO = new cursoVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM curso WHERE id_curso = ? ");
            select.setInt(1, idcurso);
            rs = select.executeQuery();
            if (rs.next()) {

                curVO.setid_curso(rs.getInt("id_curso"));
                curVO.setnombre_curso(rs.getString("nombre_curso"));
                curVO.setdescripcion_curso(rs.getString("descripcion_curso"));

            }
            return curVO;
        } catch (SQLException ex) {
            Logger.getLogger(cursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarCurso(cursoVO recVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE curso SET nombre_curso=?, descripcion_curso=? WHERE id_curso=?;");

            update.setString(1, recVO.getnombre_curso());
            update.setString(2, recVO.getdescripcion_curso());
            update.setInt(3, recVO.getid_curso());

            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(cursoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public boolean eliminarCurso(long idcurso) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM curso WHERE id_curso = ?");
            delete.setLong(1, idcurso);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }
}
