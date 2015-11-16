package VO;

public class materiaVO {
 
	private long codigo_materia;
	private String nombre_materia;
	private String descripcion;

	public long getcodigo_materia() {
		return this.codigo_materia;
	}

	public void setcodigo_materia(long codigo_materia) {
		this.codigo_materia=codigo_materia;
	}

	public String getnombre_materia() {
		return this.nombre_materia;
	}

	public void setnombre_materia(String nombre_materia) {
		this.nombre_materia=nombre_materia;
	}

	public String getdescripcion() {
		return this.descripcion;
	}

	public void setdescripcion(String descripcion) {
		this.descripcion=descripcion;
	}

}