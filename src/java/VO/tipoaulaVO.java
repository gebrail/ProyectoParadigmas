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
public class tipoaulaVO {

    public long getId_tipoaula() {
        return id_tipoaula;
    }

    public void setId_tipoaula(long id_tipoaula) {
        this.id_tipoaula = id_tipoaula;
    }

    public String getNombre_tipoaula() {
        return nombre_tipoaula;
    }

    public void setNombre_tipoaula(String nombre_tipoaula) {
        this.nombre_tipoaula = nombre_tipoaula;
    }

    public String getDescripccion_aula() {
        return descripccion_aula;
    }

    public void setDescripccion_aula(String descripccion_aula) {
        this.descripccion_aula = descripccion_aula;
    }

  private long id_tipoaula;
  private String nombre_tipoaula;
  private String descripccion_aula;
}
