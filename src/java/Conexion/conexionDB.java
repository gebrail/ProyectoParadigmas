/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 *
 * @author Wilson
 */
public class conexionDB {

    private String login = "wilson";
    private String password = "tik0dcmg";
    private String url = "jdbc:postgresql://localhost:5432/school";
    private Connection conn;

    /*
     * Constructor de connexion
     */
    public conexionDB() {
        try {
            //Obtenemos el driver de para postgres
            Class.forName("org.postgresql.Driver");
            //Obtenemos la conexión
            conn = DriverManager.getConnection(url, login, password);
            if (!conn.isClosed()) {
                System.out.println("Coneccion exitosa conectado a la db de colegio");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return conn;
    }

    /*
     * Desconexion
     */
    public void desconectar() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarStatement(Statement... statements) {
        for (Statement statement : statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void cerrarResultSet(ResultSet... results) {
        for (ResultSet rs : results) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
