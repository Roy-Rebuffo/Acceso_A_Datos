package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;

	private String apellido1;

	private String apellido2;

	private Integer categoría;

	private String ciudad;
	
	private String nombre;

	public Cliente() {
	}

	public Cliente(int id, String nombre, String apellido1, String apellido2,  String ciudad,Integer categoría) {
		super();
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.categoría = categoría;
		this.ciudad = ciudad;
		this.id = id;
		this.nombre = nombre;
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

	public Integer getCategoría() {
		return this.categoría;
	}

	public void setCategoría(Integer categoría) {
		this.categoría = categoría;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
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