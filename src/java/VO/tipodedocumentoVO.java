package VO;

public class tipodedocumentoVO {
 
	private long id_documento;
	private String nombre_documento;
	private String descripcion_documento;

	public long getid_documento() {
		return this.id_documento;
	}

	public void setid_documento(long id_documento) {
		this.id_documento=id_documento;
	}

	public String getnombre_documento() {
		return this.nombre_documento;
	}

	public void setnombre_documento(String nombre_documento) {
		this.nombre_documento=nombre_documento;
	}

	public String getdescripcion_documento() {
		return this.descripcion_documento;
	}

	public void setdescripcion_documento(String descripcion_documento) {
		this.descripcion_documento=descripcion_documento;
	}

}