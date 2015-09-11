package VO;

public class cursoVO {
 
	private int id_curso;
	private String nombre_curso;
	private String descripcion_curso;

	public int getid_curso() {
		return this.id_curso;
	}

	public void setid_curso(int id_curso) {
		this.id_curso=id_curso;
	}

	public String getnombre_curso() {
		return this.nombre_curso;
	}

	public void setnombre_curso(String nombre_curso) {
		this.nombre_curso=nombre_curso;
	}

	public String getdescripcion_curso() {
		return this.descripcion_curso;
	}

	public void setdescripcion_curso(String descripcion_curso) {
		this.descripcion_curso=descripcion_curso;
	}

}