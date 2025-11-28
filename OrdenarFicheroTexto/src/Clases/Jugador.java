package Clases;

import java.time.LocalDate;
import java.util.Date;

public class Jugador {
	private LocalDate cumple;
	private String club;
	
	public Jugador(LocalDate cumple, String club) {
		super();
		this.cumple = cumple;
		this.club = club;
	}
	public LocalDate getCumple() {
		return cumple;
	}
	public void setCumple(LocalDate cumple) {
		this.cumple = cumple;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	
	@Override
	public String toString() {
		return "Jugador [cumple=" + cumple + ", club=" + club + "]";
	}
	
	
}
