package Clases;

import java.util.Date;

public class Jugador {
    
    private String nombre_apellido; 
    private int edad; 
    private Date cumpleaños; 
    private String liga; 
    private String temporada; 
    private String posicion; 
    private int club_id; 
    private String nacionalidad; 
    private int goles_totales; 
    private int goles_local; 
    private int goles_visitante; 
    private int goles_penalty; 
    private int fallos_penalty;
    
    public Jugador(String nombre_apellido, int edad, Date cumpleaños, String liga, 
                   String temporada, String posicion, int club_id, String nacionalidad, 
                   int goles_totales, int goles_local, int goles_visitante, 
                   int goles_penalty, int fallos_penalty) {
        
        this.nombre_apellido = nombre_apellido;
        this.edad = edad;
        this.cumpleaños = cumpleaños;
        this.liga = liga;
        this.temporada = temporada;
        this.posicion = posicion;
        this.club_id = club_id;
        this.nacionalidad = nacionalidad;
        this.goles_totales = goles_totales;
        this.goles_local = goles_local;
        this.goles_visitante = goles_visitante;
        this.goles_penalty = goles_penalty;
        this.fallos_penalty = fallos_penalty;
    }
    
	public String getNombre_apellido() {
		return nombre_apellido;
	}
	public void setNombre_apellido(String nombre_apellido) {
		this.nombre_apellido = nombre_apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getCumpleaños() {
		return cumpleaños;
	}

	public void setCumpleaños(Date cumpleaños) {
		this.cumpleaños = cumpleaños;
	}

	public String getLiga() {
		return liga;
	}
	public void setLiga(String liga) {
		this.liga = liga;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getClub_id() {
		return club_id;
	}
	public void setClub_id(int club_id) {
		this.club_id = club_id;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getGoles_totales() {
		return goles_totales;
	}
	public void setGoles_totales(int goles_totales) {
		this.goles_totales = goles_totales;
	}
	public int getGoles_local() {
		return goles_local;
	}
	public void setGoles_local(int goles_local) {
		this.goles_local = goles_local;
	}
	public int getGoles_visitante() {
		return goles_visitante;
	}
	public void setGoles_visitante(int goles_visitante) {
		this.goles_visitante = goles_visitante;
	}
	public int getGoles_penalty() {
		return goles_penalty;
	}
	public void setGoles_penalty(int goles_penalty) {
		this.goles_penalty = goles_penalty;
	}
	public int getFallos_penalty() {
		return fallos_penalty;
	}
	public void setFallos_penalty(int fallos_penalty) {
		this.fallos_penalty = fallos_penalty;
	}

	@Override
	public String toString() {
		return nombre_apellido + "," + edad + "," + cumpleaños
				+ "," + liga + "," + temporada + "," + posicion + "," + club_id
				+ "," + nacionalidad + "," + goles_totales + "," + goles_local
				+ "," + goles_visitante + "," + goles_penalty + ","
				+ fallos_penalty;
	}
}
