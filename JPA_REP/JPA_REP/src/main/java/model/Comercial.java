package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comercial database table.
 * 
 */
@Entity
@NamedQuery(name="Comercial.findAll", query="SELECT c FROM Comercial c")
public class Comercial implements Serializable {
	private static final long serialVersionUID = 1L;

	private String apellido1;

	private String apellido2;

	private float comisión;
	
	@Id
	private int id;

	private String nombre;

	public Comercial() {
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public float getComisión() {
		return this.comisión;
	}

	public void setComisión(float comisión) {
		this.comisión = comisión;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}