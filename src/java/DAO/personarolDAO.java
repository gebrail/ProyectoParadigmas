package DAO;

import Conexion.conexionDB;
import VO.Cuentas;
import VO.admine;
import VO.funcionalidadVO;
import VO.personarolVO;
import VO.personasVO;
import VO.profesores;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.ArrayBufferView.buffer;

/**
 *
 * @author wilson
 */
public class personarolDAO {

    public personarolDAO() {
    }

    public personarolVO validarPersona(String username, String password) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        personarolVO loginVO = null;
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personarol WHERE username=? AND password=?;");
            select.setString(1, username);
            select.setString(2, password);
            rs = select.executeQuery();
            if (rs.next()) {
                loginVO = new personarolVO();
                loginVO.setid_persona(rs.getInt("id_persona"));
                loginVO.setusername(rs.getString("username"));
                loginVO.setpassword(rs.getString("password"));

            }
            return loginVO;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    // en esta funcion me traigo el link de las funcionalidades con su respectiva direccion :D   
    public LinkedList menu(personarolVO lapersonaVO) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            //uso la sentencia inner join para solo traerme los campos que esten en ambas tablas 1 sola vez
            select = cn.getConnection().prepareStatement("SELECT DISTINCT f.* FROM (funcionalidad AS f INNER JOIN(funcionalidaddelrol AS rf INNER JOIN personarol AS pr ON pr.id_rol = rf.id_rol)ON rf.id_funcionalidad = f.id_funcionalidad)WHERE pr.id_persona =?");
            select.setLong(1, lapersonaVO.getid_persona());
            rs = select.executeQuery();
            while (rs.next()) {
                funcionalidadVO funcVO = new funcionalidadVO();
                funcVO.setid_funcionalidad(rs.getInt("id_funcionalidad"));
                funcVO.setnombre_funcionalidad(rs.getString("nombre_funcionalidad"));
                funcVO.setdescripcion_funcionalidad(rs.getString("descripcion_funcionalidad"));
                funcVO.setlink_funcionalidad(rs.getString("link_funcionalidad"));
                funcVO.seticono_funcionalidad(rs.getString("icono_funcionalidad"));
                datos.add(funcVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    //en este metodo o funcion :p me traigo los nombres de la persona que este logeada
    public LinkedList nombresyapellidos(personarolVO elpersonaVO) throws IOException {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personas WHERE id_persona =? ");
            select.setLong(1, elpersonaVO.getid_persona());
            rs = select.executeQuery();
            while (rs.next()) {
                personasVO personaxdVO = new personasVO();
                personaxdVO.setid_persona(rs.getLong("id_persona"));
                personaxdVO.setprimernombre_persona(rs.getString("primernombre_persona"));
                personaxdVO.setsegundonombre_persona(rs.getString("segundonombre_persona"));
                personaxdVO.setprimerapellido_persona(rs.getString("primerapellido_persona"));
                personaxdVO.setsegundoapellido_persona(rs.getString("segundoapellido_persona"));
//                personaxdVO.setfoto_persona(ConvertirImagen(rs.getBytes("foto_persona")));
                datos.add(personaxdVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    private Image ConvertirImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        return reader.read(0, param);
    }

//    public byte[] obtenImagenProducto(int idProducto) {
//        conexionDB cn = null;
//        ResultSet rs = null;
//        PreparedStatement pr = null;
//        byte[] buffer = null;
//        try {
//            Class.forName(classFor);
//            cn = DriverManager.getConnection(, usuario, clave);
//            String sql = "SELECT * FROM personas WHERE id_persona =?";
//            pr = cn.prepareStatement(sql);
//            pr.setInt(1, idProducto);
//            rs = pr.executeQuery();
//            while (rs.next()) {
//                Blob bin = rs.getBlob("fotoProducto");
//                if (bin != null) {
//                    InputStream inStream = bin.getBinaryStream();
//                    int size = (int) bin.length();
//                    buffer = new byte[size];
//                    int length = -1;
//                    int k = 0;
//                    try {
//                        inStream.read(buffer, 0, size);
//                    } catch (IOException ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            return null;
//        } finally {
//            cn = null;
//            rs = null;
//            pr = null;
//        }
//        return buffer;
//    }
    public byte[] obtenImagenProducto(long idProducto) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        byte[] buffer = null;
        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personas WHERE id_persona =? ");
            select.setLong(1, idProducto);
            rs = select.executeQuery();
            while (rs.next()) {
                Blob bin = rs.getBlob("foto_persona");
                if (bin != null) {
                    InputStream inStream = bin.getBinaryStream();
                    int size = (int) bin.length();
                    buffer = new byte[size];
                    int length = -1;
                    int k = 0;
                    try {
                        inStream.read(buffer, 0, size);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
            return buffer;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return buffer;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean registrarCuenta(String username, String password, long idpersona, int idrol) {
        conexionDB cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexionDB();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO personarol(username, password, id_persona, id_rol)VALUES (?, ?, ?, ?);");
            insert.setString(1, username);
            insert.setString(2, password);
            insert.setLong(3, idpersona);
            insert.setInt(4, idrol);
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            } else {
                registro = false;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(personasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarcuentas() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT personarol.username,personarol.password, personarol.id_persona,rol.nombre_rol FROM public.personarol,public.rol WHERE rol.id_rol = personarol.id_rol order by username asc;");
            rs = select.executeQuery();
            while (rs.next()) {
                Cuentas cuentaVO = new Cuentas();
                cuentaVO.setusername(rs.getString("username"));
                cuentaVO.setpassword(rs.getString("password"));
                cuentaVO.setid_persona(rs.getLong("id_persona"));
                cuentaVO.setid_rol(rs.getString("nombre_rol"));
                datos.add(cuentaVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList consultarCuenta(String cuentausername) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();

        try {
            cn = new conexionDB();
            select = cn.getConnection().prepareStatement("SELECT * FROM personarol WHERE username ~* ? ");
            select.setString(1, cuentausername);
            rs = select.executeQuery();
            while (rs.next()) {
                personarolVO personrolVO = new personarolVO();
                personrolVO.setusername(rs.getString("username"));
                personrolVO.setpassword(rs.getString("password"));
                personrolVO.setid_persona(rs.getLong("id_persona"));
                personrolVO.setid_rol(rs.getInt("id_rol"));
                datos.add(personrolVO);

            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public personarolVO consultaIndividualCuenta(String username) {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        try {
            cn = new conexionDB();
            personarolVO recVO = new personarolVO();
            select = cn.getConnection().prepareStatement("SELECT * FROM personarol WHERE username=? ");
            select.setString(1, username);
            rs = select.executeQuery();
            if (rs.next()) {
                recVO.setusername(rs.getString("username"));
                recVO.setpassword(rs.getString("password"));
                recVO.setid_persona(rs.getLong("id_persona"));
                recVO.setid_rol(rs.getInt("id_rol"));
            }
            return recVO;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarCuenta(personarolVO recVO) {
        conexionDB cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexionDB();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE personarol SET password=?, id_persona=?, id_rol=? WHERE username=?;");
            update.setString(1, recVO.getpassword());
            update.setLong(2, recVO.getid_persona());
            update.setInt(3, recVO.getid_rol());
            update.setString(4, recVO.getusername());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public boolean eliminarCuenta(String username) {
        conexionDB cn = null;
        PreparedStatement delete = null;
        try {
            cn = new conexionDB();
            boolean eliminar = false;
            delete = cn.getConnection().prepareStatement("DELETE FROM personarol WHERE username = ?");
            delete.setString(1, username);
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

    public LinkedList listarprofesores() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT personas.id_persona,personas.primernombre_persona,personas.primerapellido_persona,rol.nombre_rol,grupo.nombre_grupo,grupo.id_grupo FROM public.personas,public.grupo,public.personarol,public.rol WHERE personas.id_persona = personarol.id_persona AND grupo.id_persona = personas.id_persona AND personarol.id_rol = rol.id_rol;");
            rs = select.executeQuery();
            while (rs.next()) {
                profesores profesorVO = new profesores();
                profesorVO.setId_person(rs.getLong("id_persona"));
                profesorVO.setPrimernombre_persona(rs.getString("primernombre_persona"));
                profesorVO.setPrimerapellido_persona(rs.getString("primerapellido_persona"));
                profesorVO.setNombre_rol(rs.getString("nombre_rol"));
                profesorVO.setNombre_grupo(rs.getString("nombre_grupo"));
                profesorVO.setId_grupo(rs.getLong("id_grupo"));

                datos.add(profesorVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public LinkedList listaradmines() {
        conexionDB cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {

            cn = new conexionDB();
            select = cn.getConnection().prepareCall("SELECT personas.id_persona,personas.primernombre_persona,personas.primerapellido_persona,rol.nombre_rol FROM public.personas,public.personarol,public.rol WHERE personas.id_persona = personarol.id_persona AND personarol.id_rol = rol.id_rol and rol.nombre_rol = 'Administrador';");
            rs = select.executeQuery();
            while (rs.next()) {
                admine adminesVO = new admine();
                adminesVO.setId_persona(rs.getLong("id_persona"));
                adminesVO.setPrimernombre_persona(rs.getString("primernombre_persona"));
                adminesVO.setPrimerapellido_persona(rs.getString("primerapellido_persona"));
                adminesVO.setNombre_rol(rs.getString("nombre_rol"));

                datos.add(adminesVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(personarolDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

}
