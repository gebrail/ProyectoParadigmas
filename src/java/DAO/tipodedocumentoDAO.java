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
                tidoVO.setdescripcion_documento(rs.getString("nombre_documento"));
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
}

