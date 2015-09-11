package VO;

public class materiaestudiantesVO {

    private int id_persona;
    private int codigo_materia;
    private double nota1;
    private double nota2;
    private double nota3;
    private boolean aprobacion;

    public int getid_persona() {
        return this.id_persona;
    }

    public void setid_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getcodigo_materia() {
        return this.codigo_materia;
    }

    public void setcodigo_materia(int codigo_materia) {
        this.codigo_materia = codigo_materia;
    }

    public double getnota1() {
        return this.nota1;
    }

    public void setnota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getnota2() {
        return this.nota2;
    }

    public void setnota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getnota3() {
        return this.nota3;
    }

    public void setnota3(double nota3) {
        this.nota3 = nota3;
    }

    public boolean getaprobacion() {
        return this.aprobacion;
    }

    public void setaprobacion(boolean aprobacion) {
        this.aprobacion = aprobacion;
    }

}
