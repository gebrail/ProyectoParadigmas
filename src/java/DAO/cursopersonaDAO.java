/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.cursopersonaVO;
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
public class cursopersonaDAO {

    public cursopersonaDAO() {

    }

    public boolean AsignarCursoPersona(long idpersona, long idcurso) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO cursopersona(id_persona, id_curso) VALUES (?, ?);");
            insert.setLong(1, idpersona);
            insert.setLong(2, idcurso);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(cursopersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarpersonascursos() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * from cursopersona order by id_curso  asc;");
            rs = select.executeQuery();
            while (rs.next()) {
                cursopersonaVO curVO = new cursopersonaVO();
                curVO.setid_curso(rs.getInt("id_curso"));
                curVO.setid_persona(rs.getLong("id_persona"));
                datos.add(curVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(cursopersonaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consultarpersonacurso(long elcurso) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM cursopersona WHERE id_persona=?;");
            select.setLong(1, elcurso);
            rs = select.executeQuery();
            while (rs.next()) {
                cursopersonaVO cursoxdVO = new cursopersonaVO();
                cursoxdVO.setid_curso(rs.getInt("id_curso"));
                cursoxdVO.setid_persona(rs.getLong("id_persona"));
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

}
