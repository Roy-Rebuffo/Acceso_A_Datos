package Clases;

import java.util.Date;

public class Partidos {
	private int idjornada;
	private int idlocal;
	private int idvisitante;
	private int gol_local;
	private int gol_visitante;
	private Date fecha;
	
	public Partidos(int idjornada, int idlocal, int idvisitante, int gol_local, int gol_visitante, Date fecha) {
		super();
		this.idjornada = idjornada;
		this.idlocal = idlocal;
		this.idvisitante = idvisitante;
		this.gol_local = gol_local;
		this.gol_visitante = gol_visitante;
		this.fecha = fecha;
	}

	public int getIdjornada() {
		return idjornada;
	}

	public void setIdjornada(int idjornada) {
		this.idjornada = idjornada;
	}

	public int getIdlocal() {
		return idlocal;
	}

	public void setIdlocal(int idlocal) {
		this.idlocal = idlocal;
	}

	public int getIdvisitante() {
		return idvisitante;
	}

	public void setIdvisitante(int idvisitante) {
		this.idvisitante = idvisitante;
	}

	public int getGol_local() {
		return gol_local;
	}

	public void setGol_local(int gol_local) {
		this.gol_local = gol_local;
	}

	public int getGol_visitante() {
		return gol_visitante;
	}

	public void setGol_visitante(int gol_visitante) {
		this.gol_visitante = gol_visitante;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "idjornada=" + idjornada + ", idlocal=" + idlocal + ", idvisitante=" + idvisitante
				+ ", gol_local=" + gol_local + ", gol_visitante=" + gol_visitante + ", fecha=" + fecha;
	}
	
	
	
}
