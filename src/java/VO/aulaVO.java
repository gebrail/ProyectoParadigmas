package VO;

/**
 *
 * @author wilso
 */
public class aulaVO {

    public long getId_aula() {
        return id_aula;
    }

    public void setId_aula(long id_aula) {
        this.id_aula = id_aula;
    }

    public String getNombre_aula() {
        return nombre_aula;
    }

    public void setNombre_aula(String nombre_aula) {
        this.nombre_aula = nombre_aula;
    }

    public long getId_tipoaula() {
        return id_tipoaula;
    }

    public void setId_tipoaula(long id_tipoaula) {
        this.id_tipoaula = id_tipoaula;
    }

    private long id_aula;
    private String nombre_aula;
    private long id_tipoaula;

}
