package Clases;

public class Equipo {
    private int id;
	private String nombre_club;
	
	//Crea autoIncrement id
	public Equipo(String nombre_club) {
		super();
		this.nombre_club = nombre_club;
	}
	//Para leer al equipo desde la BBDD
	public Equipo(int id, String nombre_club) {
		super();
		this.id = id;
		this.nombre_club = nombre_club;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_club() {
		return nombre_club;
	}

	public void setNombre_club(String nombre_club) {
		this.nombre_club = nombre_club;
	}

	@Override
	public String toString() {
		return nombre_club;
	} 
}
