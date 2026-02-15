package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the equipos database table.
 * 
 */
@Entity
@Table(name="equipos")
@NamedQuery(name="Equipo.findAll", query="SELECT e FROM Equipo e")
public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idequipo;

	private String nombre;

	private String logo;

	public Equipo() {
	}

	public int getIdequipo() {
		return this.idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public void reset() {
		this.nombre = null;
	}
}