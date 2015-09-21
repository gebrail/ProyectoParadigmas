package VO;

public class personarolVO {
 
	private String username;
	private String password;
	private long id_persona;
	private int id_rol;

	public String getusername() {
		return this.username;
	}

	public void setusername(String username) {
		this.username=username;
	}

	public String getpassword() {
		return this.password;
	}

	public void setpassword(String password) {
		this.password=password;
	}

	public long getid_persona() {
		return this.id_persona;
	}

	public void setid_persona(long id_persona) {
		this.id_persona=id_persona;
	}

	public int getid_rol() {
		return this.id_rol;
	}

	public void setid_rol(int id_rol) {
		this.id_rol=id_rol;
	}

}