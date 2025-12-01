package Clases;

import java.util.Date;

public class Jugador {

	private String nombre;
	private Date cumple;
	private String equipo;

	public Date getCumple() {
		return cumple;
	}

	public void setCumple(Date cumple) {
		this.cumple = cumple;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public Jugador(String nombre, Date cumple, String equipo) {
		super();
		this.nombre = nombre;
		this.cumple = cumple;
		this.equipo = equipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "\n > Jugador " + nombre + "\n\n < BIRTHDAY >" + cumple + "\n < TEAM > " + equipo;
	}
}
