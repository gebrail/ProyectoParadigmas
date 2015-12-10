package VO;import java.awt.Image;
import java.io.InputStream;
import java.sql.Date;


public class personasVO {
 
	private long id_persona;
	private int id_documento;
	private String primernombre_persona;
	private String segundonombre_persona;
	private String primerapellido_persona;
	private String segundoapellido_persona;
	private InputStream  foto_persona;
	private String genero_persona;
	private String direccion_persona;
	private long telefono_persona;
	private String correo_persona;
	private String estadocivil_persona;
	private Date fechanacimiento_persona;

	public long getid_persona() {
		return this.id_persona;
	}

	public void setid_persona(long id_persona) {
		this.id_persona=id_persona;
	}

	public int getid_documento() {
		return this.id_documento;
	}

	public void setid_documento(int id_documento) {
		this.id_documento=id_documento;
	}

	public String getprimernombre_persona() {
		return this.primernombre_persona;
	}

	public void setprimernombre_persona(String primernombre_persona) {
		this.primernombre_persona=primernombre_persona;
	}

	public String getsegundonombre_persona() {
		return this.segundonombre_persona;
	}

	public void setsegundonombre_persona(String segundonombre_persona) {
		this.segundonombre_persona=segundonombre_persona;
	}

	public String getprimerapellido_persona() {
		return this.primerapellido_persona;
	}

	public void setprimerapellido_persona(String primerapellido_persona) {
		this.primerapellido_persona=primerapellido_persona;
	}

	public String getsegundoapellido_persona() {
		return this.segundoapellido_persona;
	}

	public void setsegundoapellido_persona(String segundoapellido_persona) {
		this.segundoapellido_persona=segundoapellido_persona;
	}

	public InputStream getfoto_persona() {
		return this.foto_persona;
	}

    public void setfoto_persona(InputStream foto_persona) {
		this.foto_persona=foto_persona;
	}

	public String getgenero_persona() {
		return this.genero_persona;
	}

	public void setgenero_persona(String genero_persona) {
		this.genero_persona=genero_persona;
	}

	public String getdireccion_persona() {
		return this.direccion_persona;
	}

	public void setdireccion_persona(String direccion_persona) {
		this.direccion_persona=direccion_persona;
	}

	public long gettelefono_persona() {
		return this.telefono_persona;
	}

	public void settelefono_persona(long telefono_persona) {
		this.telefono_persona=telefono_persona;
	}

	public String getcorreo_persona() {
		return this.correo_persona;
	}

	public void setcorreo_persona(String correo_persona) {
		this.correo_persona=correo_persona;
	}

	public String getestadocivil_persona() {
		return this.estadocivil_persona;
	}

	public void setestadocivil_persona(String estadocivil_persona) {
		this.estadocivil_persona=estadocivil_persona;
	}

	public Date getfechanacimiento_persona() {
		return this.fechanacimiento_persona;
	}

	public void setfechanacimiento_persona(Date fechanacimiento_persona) {
		this.fechanacimiento_persona=fechanacimiento_persona;
	}

}