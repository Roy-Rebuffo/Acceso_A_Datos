package com.example.demo.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the partidos database table.
 * 
 */
@Entity
@Table(name="partidos")
@NamedQuery(name="Partido.findAll", query="SELECT p FROM Partido p")
public class Partido implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PartidoPK id;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	@Column(name="GOL_LOCAL")
	private int golLocal;

	@Column(name="GOL_VISITANTE")
	private int golVisitante;

	public Partido() {
	}

	public PartidoPK getId() {
		return this.id;
	}

	public void setId(PartidoPK id) {
		this.id = id;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getGolLocal() {
		return this.golLocal;
	}

	public void setGolLocal(int golLocal) {
		this.golLocal = golLocal;
	}

	public int getGolVisitante() {
		return this.golVisitante;
	}

	public void setGolVisitante(int golVisitante) {
		this.golVisitante = golVisitante;
	}

}