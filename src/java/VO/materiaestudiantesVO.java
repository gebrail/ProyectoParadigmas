package VO;

public class materiaestudiantesVO {

    public long getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(long id_grupo) {
        this.id_grupo = id_grupo;
    }

    public long getId_persona() {
        return id_persona;
    }

    public void setId_persona(long id_persona) {
        this.id_persona = id_persona;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public boolean isAprobacion() {
        return aprobacion;
    }

    public void setAprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

    private long id_grupo;
    private long id_persona;
    private double nota1;
    private double nota2;
    private double nota3;
    private boolean aprobacion;


}
