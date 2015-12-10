/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.cursoVO;
import VO.grupo;
import VO.materiaVO;
import VO.materiaestudiantesVO;
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
public class materiaDAO {

    public materiaDAO() {

    }

    public LinkedList listarcurso() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaroles = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("select * from curso;");
            rs = select.executeQuery();
            while (rs.next()) {
                cursoVO cursosVO = new cursoVO();
                cursosVO.setid_curso(rs.getInt("id_curso"));
                cursosVO.setnombre_curso(rs.getString("nombre_curso"));
                cursosVO.setdescripcion_curso(rs.getString("descripcion_curso"));
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

    public boolean registrarMateria(long idmateria, String nobmremateria, String descripcion) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO materia(codigo_materia, nombre_materia, descripcion) VALUES (?, ?, ?);");
            insert.setLong(1, idmateria);
            insert.setString(2, nobmremateria);
            insert.setString(3, descripcion);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarmaterias() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT codigo_materia, nombre_materia, descripcion FROM materia order by codigo_materia;");
            rs = select.executeQuery();
            while (rs.next()) {
                materiaVO matVO = new materiaVO();
                matVO.setcodigo_materia(rs.getLong("codigo_materia"));
                matVO.setnombre_materia(rs.getString("nombre_materia"));
                matVO.setdescripcion(rs.getString("descripcion"));
                datos.add(matVO);
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

    public LinkedList consultarMateria(String elcurso) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM materia WHERE nombre_materia~* ? ");
            select.setString(1, elcurso);
            rs = select.executeQuery();
            while (rs.next()) {
                materiaVO materiaxdVO = new materiaVO();
                materiaxdVO.setcodigo_materia(rs.getLong("codigo_materia"));
                materiaxdVO.setnombre_materia(rs.getString("nombre_materia"));
                materiaxdVO.setdescripcion(rs.getString("descripcion"));
                datos.add(materiaxdVO);

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

    public materiaVO consultaIndividualMateria(long idmateria) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            materiaVO matVO = new materiaVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM materia WHERE codigo_materia = ? ");
            select.setLong(1, idmateria);
            rs = select.executeQuery();
            if (rs.next()) {
                matVO.setcodigo_materia(rs.getLong("codigo_materia"));
                matVO.setnombre_materia(rs.getString("nombre_materia"));
                matVO.setdescripcion(rs.getString("descripcion"));
            }
            return matVO;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarMateria(materiaVO matVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE materia SET nombre_materia=?, descripcion=? WHERE codigo_materia=?;");
            update.setString(1, matVO.getnombre_materia());
            update.setString(2, matVO.getdescripcion());
            update.setLong(3, matVO.getcodigo_materia());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public boolean eliminarMateria(long idcurso) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM materia WHERE codigo_materia = ?");
            delete.setLong(1, idcurso);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

    public LinkedList consultargruposdelamateria(long materia) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM grupo WHERE codigo_materia=? ;");
            select.setLong(1, materia);
            rs = select.executeQuery();
            while (rs.next()) {
                grupo elgrupoVO = new grupo();
                elgrupoVO.setId_grupo(rs.getLong("id_grupo"));
                elgrupoVO.setCodigo_materia(rs.getLong("codigo_materia"));
                elgrupoVO.setId_persona(rs.getLong("id_persona"));
                elgrupoVO.setNombre_grupo(rs.getString("nombre_grupo"));
                elgrupoVO.setDescripccion(rs.getString("descripccion"));
                datos.add(elgrupoVO);

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

    public LinkedList consultargruposdelamateriadelestudiante(long materia, long idper) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT mt.* FROM public.grupo,public.materiaestudiantes as mt WHERE grupo.id_grupo = mt.id_grupo and grupo.codigo_materia = ? and mt.id_persona =?;");
            select.setLong(1, materia);
            select.setLong(2, idper);
            rs = select.executeQuery();
            while (rs.next()) {
                materiaestudiantesVO mta = new materiaestudiantesVO();
                mta.setId_grupo(rs.getLong("id_grupo"));
                mta.setId_persona(rs.getLong("id_persona"));
                datos.add(mta);

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

    public materiaestudiantesVO consultaIndividualMateriaestudiantes(long idgrupo, long idpersona) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            materiaestudiantesVO matVO = new materiaestudiantesVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM materiaestudiantes where materiaestudiantes.id_grupo= ? and materiaestudiantes.id_persona =?; ");
            select.setLong(1, idgrupo);
            select.setLong(2, idpersona);
            rs = select.executeQuery();
            if (rs.next()) {
                matVO.setNota1(rs.getDouble("nota1"));
                matVO.setNota2(rs.getDouble("nota2"));
                matVO.setNota3(rs.getDouble("nota3"));
             matVO.setId_persona(rs.getLong("id_persona"));
             matVO.setId_grupo(rs.getLong("id_grupo"));
            }
            return matVO;
        } catch (SQLException ex) {
            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }
    
    
     public LinkedList consultargruposdelamateriaest(long materia, long idper) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT materiaestudiantes.* FROM public.materiaestudiantes, public.grupo WHERE materiaestudiantes.id_grupo = grupo.id_grupo and materiaestudiantes.id_persona=? and grupo.codigo_materia=?;");
            select.setLong(1, idper);
            select.setLong(2, materia);
            rs = select.executeQuery();
            while (rs.next()) {
                materiaestudiantesVO xd = new materiaestudiantesVO();
                xd.setId_grupo(rs.getLong("id_grupo"));
                xd.setId_persona(rs.getLong("id_persona"));
                
                datos.add(xd);

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

}
