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
public class Cuentas {
 
	private String username;
	private String password;
	private long id_persona;
	private String el_rol;

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

	public String getid_rol() {
		return this.el_rol;
	}

	public void setid_rol(String id_rol) {
		this.el_rol=id_rol;
	}

}
