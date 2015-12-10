/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexionDB;
import VO.grupo;
import VO.materiaVO;
import VO.materiaestudiantesVO;
import VO.personasVO;
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
public class materiaestudiantesDAO {

    public materiaestudiantesDAO() {

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

    public LinkedList listarmateriasqueven(long x) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT m.* FROM public.materia as m,public.materiaestudiantes,public.grupo WHERE m.codigo_materia = grupo.codigo_materia AND materiaestudiantes.id_grupo = grupo.id_grupo and materiaestudiantes.id_persona= ? group by m.codigo_materia,m.nombre_materia,m.descripcion ;");
            select.setLong(1, x);
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
            Logger.getLogger(materiaestudiantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consultagrupoasignado(long idper) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();//id_grupo, id_persona, nota1, nota2, nota3, aprobacion
            select = cn.getConnection().prepareStatement("SELECT gr.* FROM public.grupo as gr WHERE not exists(select 1 from public.materiaestudiantes as mat where mat.id_grupo=gr.id_grupo and mat.id_persona=?);");
            //select = cn.getConnection().prepareStatement("SELECT gr.* FROM public.grupo as gr WHERE not exists(select 1 from public.materiaestudiantes as mat,public.materia as lm where mat.id_grupo=gr.id_grupo and gr.codigo_materia=lm.codigo_materia and mat.id_persona=?);");
            select.setLong(1, idper);
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

    public boolean AsignarMateriaGrupo(long idgrupo, long idpersona) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO materiaestudiantes(id_grupo, id_persona) VALUES (?, ?);");
            insert.setLong(1, idgrupo);
            insert.setLong(2, idpersona);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;

        } catch (SQLException ex) {
            Logger.getLogger(materiaestudiantesDAO.class
                    .getName()).log(Level.SEVERE, null, ex);

            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList consultarMateriaPersona(String nombre) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT p.* FROM public.personas as p,public.personarol as pr, public.rol as r WHERE p.id_persona = pr.id_persona AND pr.id_rol = r.id_rol and r.nombre_rol='Estudiante' and  primerapellido_persona ~* ?;");
            select.setString(1, nombre);
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO personVO = new personasVO();
                personVO.setid_persona(rs.getLong("id_persona"));
                personVO.setid_documento(rs.getInt("id_documento"));
                personVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
//                personVO.setfoto_persona(rs.getString("foto_persona"));
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
//                personVO.setfoto_persona(rs.getString("foto_persona"));
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

//        public materiaestudiantesVO consultaIndividualMateriaPersona(long idpersona) {
//
//        conexionDB cn = null;
//        PreparedStatement select = null;
//        ResultSet rs = null;
//        try {
//            cn = new conexionDB();
//            materiaVO matVO = new materiaVO();
//            select = cn.getConnection().prepareStatement("SELECT * FROM materia WHERE codigo_materia = ? ");
//            select.setLong(1, idpersona);
//            rs = select.executeQuery();
//            if (rs.next()) {
//                matVO.setcodigo_materia(rs.getLong("codigo_materia"));
//                matVO.setnombre_materia(rs.getString("nombre_materia"));
//                matVO.setdescripcion(rs.getString("descripcion"));
//            }
//            return matVO;
//        } catch (SQLException ex) {
//            Logger.getLogger(materiaDAO.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } finally {
//            cn.desconectar();
//            cn.cerrarStatement(select);
//            cn.cerrarResultSet(rs);
//        }
//    }
    public boolean modificarGrupoAsignado(materiaestudiantesVO matVO, long idgr) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE materiaestudiantes SET id_grupo=? WHERE  id_persona=? and id_grupo=?;");
            update.setLong(1, matVO.getId_grupo());
            update.setLong(2, matVO.getId_persona());
            update.setLong(3, idgr);
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(materiaestudiantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public LinkedList consultarmateriaasignadaspr(long idper) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();//id_grupo, id_persona, nota1, nota2, nota3, aprobacion
            select = cn.getConnection().prepareStatement("SELECT mat.* FROM public.materia as mat WHERE exists(select 1 from public.grupo as gr where gr.codigo_materia=mat.codigo_materia and gr.id_persona=?);");
            //select = cn.getConnection().prepareStatement("SELECT gr.* FROM public.grupo as gr WHERE not exists(select 1 from public.materiaestudiantes as mat,public.materia as lm where mat.id_grupo=gr.id_grupo and gr.codigo_materia=lm.codigo_materia and mat.id_persona=?);");
            select.setLong(1, idper);
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

    public LinkedList consultagrupoasignadopr(long idpe) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();//id_grupo, id_persona, nota1, nota2, nota3, aprobacion
            select = cn.getConnection().prepareStatement("SELECT \n"
                    + "*\n"
                    + "FROM \n"
                    + "  grupo\n"
                    + "  where id_persona=?;");
            //select = cn.getConnection().prepareStatement("SELECT gr.* FROM public.grupo as gr WHERE not exists(select 1 from public.materiaestudiantes as mat,public.materia as lm where mat.id_grupo=gr.id_grupo and gr.codigo_materia=lm.codigo_materia and mat.id_persona=?);");
            select.setLong(1, idpe);
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

    public LinkedList listarestgr(long x) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT * FROM materiaestudiantes where id_grupo=?;");
            select.setLong(1, x);
            rs = select.executeQuery();
            while (rs.next()) {
                materiaestudiantesVO matVO = new materiaestudiantesVO();
                matVO.setId_grupo(rs.getLong("id_grupo"));
                matVO.setId_persona(rs.getLong("id_persona"));
                datos.add(matVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(materiaestudiantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public materiaestudiantesVO consultaIndividualNE(long idgr, long idper) {

        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            materiaestudiantesVO matVO = new materiaestudiantesVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM materiaestudiantes where id_grupo=? and id_persona=?;");
            select.setLong(1, idgr);
            select.setLong(2, idper);
            rs = select.executeQuery();
            if (rs.next()) {

                matVO.setId_grupo(rs.getLong("id_grupo"));
                matVO.setId_persona(rs.getLong("id_persona"));
                matVO.setNota1(rs.getDouble("nota1"));
                matVO.setNota2(rs.getDouble("nota2"));
                matVO.setNota3(rs.getDouble("nota3"));

            }
            return matVO;
        } catch (SQLException ex) {
            Logger.getLogger(materiaestudiantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean InsertarNotas(materiaestudiantesVO matV) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            // JOptionPane.showMessageDialog(null, matVO.getNota1()+ "\n"+ matVO.getNota2() +"\n"+matVO.getNota3()+"\n"+ matVO.getId_grupo()+"\n"+ matVO.getId_persona());
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE materiaestudiantes SET nota1=?, nota2=?, nota3=? WHERE  id_grupo=? and id_persona=?;");
            update.setDouble(1, matV.getNota1());
            update.setDouble(2, matV.getNota2());
            update.setDouble(3, matV.getNota3());
            update.setLong(4, matV.getId_grupo());
            update.setLong(5, matV.getId_persona());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;

            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(materiaestudiantesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public LinkedList consultarmateriaasignadaset(long idper) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();//id_grupo, id_persona, nota1, nota2, nota3, aprobacion
            select = cn.getConnection().prepareStatement("SELECT mat.* FROM public.materia as mat WHERE exists(select 1 from public.grupo as gr, public.materiaestudiantes as est where gr.codigo_materia=mat.codigo_materia and gr.id_grupo = est.id_grupo and est.id_persona=?);");
            //select = cn.getConnection().prepareStatement("SELECT gr.* FROM public.grupo as gr WHERE not exists(select 1 from public.materiaestudiantes as mat,public.materia as lm where mat.id_grupo=gr.id_grupo and gr.codigo_materia=lm.codigo_materia and mat.id_persona=?);");
            select.setLong(1, idper);
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

}
