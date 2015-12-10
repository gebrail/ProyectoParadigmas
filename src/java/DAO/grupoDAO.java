/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.grupo;
import VO.materiaVO;
import VO.personasVO;
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
public class grupoDAO {

    public void grupoDAO() {

    }

    public LinkedList listarprofeores() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listaTipoDocumento = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT personas.id_persona FROM public.personas,public.personarol,public.rol WHERE personas.id_persona = personarol.id_persona AND  rol.nombre_rol ='Profesor' and personarol.id_rol = rol.id_rol;");
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO perVO = new personasVO();
                perVO.setid_persona(rs.getLong("id_persona"));
                listaTipoDocumento.add(perVO);
            }
            return listaTipoDocumento;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listaTipoDocumento;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList listarmaterias() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList listamaterias = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM materia");
            rs = select.executeQuery();
            while (rs.next()) {
                materiaVO perVO = new materiaVO();
                perVO.setcodigo_materia(rs.getLong("codigo_materia"));
                perVO.setnombre_materia(rs.getString("nombre_materia"));
                listamaterias.add(perVO);
            }
            return listamaterias;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return listamaterias;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrargrupo(long id_grupo, long codigo_materia, long id_persona, String nombre_grupo, String descripccion) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO grupo(id_grupo, codigo_materia, id_persona, nombre_grupo, descripccion)VALUES (?, ?, ?, ?, ?);");

            insert.setLong(1, id_grupo);
            insert.setLong(2, codigo_materia);
            insert.setLong(3, id_persona);
            insert.setString(4, nombre_grupo);
            insert.setString(5, descripccion);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

//    public LinkedList listargrupos() {
//        conexionDB cn = null;
//        PreparedStatement select = null;
//        ResultSet rs = null;
//        LinkedList datos = new LinkedList();
//        try {
//            cn = new conexionDB();
//            select = cn.getConnection().prepareCall("SELECT * from grupo order by id_grupo asc;");
//            rs = select.executeQuery();
//            while (rs.next()) {
//                grupo curVO = new grupo();
//                curVO.setId_grupo(rs.getLong("id_grupo"));
//                curVO.setId_persona(rs.getLong("id_persona"));
//                curVO.setCodigo_materia(rs.getLong("codigo_materia"));
//                curVO.setNombre_grupo(rs.getString("nombre_grupo"));
//                curVO.setDescripccion(rs.getString("descripcion"));
//                datos.add(curVO);
//            }
//            return datos;
//        } catch (SQLException ex) {
//            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return datos;
//        } finally {
//            cn.desconectar();
//            cn.cerrarStatement(select);
//            cn.cerrarResultSet(rs);
//        }
//    }
    public LinkedList listargrupos() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * from grupo order by id_grupo asc;");
            rs = select.executeQuery();
            while (rs.next()) {
                grupo grupoVO = new grupo();
                grupoVO.setId_grupo(rs.getLong("id_grupo"));
                grupoVO.setCodigo_materia(rs.getLong("codigo_materia"));
                grupoVO.setId_persona(rs.getLong("id_persona"));
                grupoVO.setNombre_grupo(rs.getString("nombre_grupo"));
                grupoVO.setDescripccion(rs.getString("descripccion"));
                datos.add(grupoVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consultarGrupo(String elgrupo) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM grupo WHERE nombre_grupo~* ? ");
            select.setString(1, elgrupo);
            rs = select.executeQuery();
            while (rs.next()) {
                grupo grupoVO = new grupo();
                grupoVO.setId_grupo(rs.getLong("id_grupo"));
                grupoVO.setCodigo_materia(rs.getLong("codigo_materia"));
                grupoVO.setId_persona(rs.getLong("id_persona"));
                grupoVO.setNombre_grupo(rs.getString("nombre_grupo"));
                grupoVO.setDescripccion(rs.getString("descripccion"));
                datos.add(grupoVO);

            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public grupo consultaIndividualGrupo(long idgrupo) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            grupo elgrupoVO = new grupo();
            select = cn.getConnection().prepareStatement("SELECT * FROM grupo where id_grupo=?; ");
            select.setLong(1, idgrupo);
            rs = select.executeQuery();
            if (rs.next()) {
                elgrupoVO.setId_grupo(rs.getLong("id_grupo"));
                elgrupoVO.setCodigo_materia(rs.getLong("codigo_materia"));
                elgrupoVO.setId_persona(rs.getLong("id_persona"));
                elgrupoVO.setNombre_grupo(rs.getString("nombre_grupo"));
                elgrupoVO.setDescripccion(rs.getString("descripccion"));

            }
            return elgrupoVO;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarGrupo(grupo elgrupoVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE grupo SET codigo_materia=?, id_persona=?, nombre_grupo=?, descripccion=?  WHERE id_grupo=?;");

            update.setLong(1, elgrupoVO.getCodigo_materia());
            update.setLong(2, elgrupoVO.getId_persona());
            update.setString(3, elgrupoVO.getNombre_grupo());
            update.setString(4, elgrupoVO.getDescripccion());
            update.setLong(5, elgrupoVO.getId_grupo());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }
    
    
        public boolean eliminarGrupo(long idgrupo) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM grupo WHERE id_grupo=?");
            delete.setLong(1, idgrupo);
            int r = delete.executeUpdate();
            if (r != 0) {
                eliminar = true;
            }
            return eliminar;
        } catch (SQLException ex) {
            Logger.getLogger(grupoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(delete);
        }
    }

}
