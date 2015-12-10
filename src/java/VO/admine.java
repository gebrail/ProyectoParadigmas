/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VO;

/**
 *
 * @author wilson
 */
public class admine {

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }

    public String getPrimernombre_persona() {
        return primernombre_persona;
    }

    public void setPrimernombre_persona(String primernombre_persona) {
        this.primernombre_persona = primernombre_persona;
    }

    public String getPrimerapellido_persona() {
        return primerapellido_persona;
    }

    public void setPrimerapellido_persona(String primerapellido_persona) {
        this.primerapellido_persona = primerapellido_persona;
    }

    public String getNombre_rol() {
        return nombre_rol;
    }

    public void setNombre_rol(String nombre_rol) {
        this.nombre_rol = nombre_rol;
    }

    private long id_persona;
    private String primernombre_persona;
    private String primerapellido_persona;
    private String nombre_rol;
}
