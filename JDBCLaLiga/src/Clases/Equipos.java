package Clases;

public class Equipos {
	private int idequipo;
	private String nombre;
	
	public Equipos(int idequipo, String nombre) {
		super();
		this.idequipo = idequipo;
		this.nombre = nombre;
	}

	public int getIdequipo() {
		return idequipo;
	}

	public void setIdequipo(int idequipo) {
		this.idequipo = idequipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "idequipo=" + idequipo + ", nombre=" + nombre;
	}
	
	
}
