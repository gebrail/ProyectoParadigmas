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
public class profesores {

    private long id_person;
    private String primernombre_persona;
    private String primerapellido_persona;
    private String nombre_rol;
    private String nombre_grupo;

    public long getId_person() {
        return id_person;
    }

    public void setId_person(long id_person) {
        this.id_person = id_person;
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

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public long getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(long id_grupo) {
        this.id_grupo = id_grupo;
    }
    private long id_grupo;

}
