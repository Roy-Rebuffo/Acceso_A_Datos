package Clases;

import java.util.Date;

public class Jornadas {
	private int idjornada;
	private Date fecha;
	
	public Jornadas(int idjornada, Date fecha) {
		super();
		this.idjornada = idjornada;
		this.fecha = fecha;
	}

	public int getIdjornada() {
		return idjornada;
	}

	public void setIdjornada(int idjornada) {
		this.idjornada = idjornada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "idjornada=" + idjornada + ", fecha=" + fecha;
	}
	
	
}
