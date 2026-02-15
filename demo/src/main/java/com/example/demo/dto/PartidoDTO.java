package com.example.demo.dto;

public class PartidoDTO {
    private String localNombre;
    private String visitanteNombre;

    private String localLogo;
    private String visitanteLogo;

    private int golesLocal;
    private int golesVisitante;

    // Getters y Setters
    public String getLocalNombre() {
        return localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }

    public String getVisitanteNombre() {
        return visitanteNombre;
    }

    public void setVisitanteNombre(String visitanteNombre) {
        this.visitanteNombre = visitanteNombre;
    }

    public String getLocalLogo() {
        return localLogo;
    }

    public void setLocalLogo(String localLogo) {
        this.localLogo = localLogo;
    }

    public String getVisitanteLogo() {
        return visitanteLogo;
    }

    public void setVisitanteLogo(String visitanteLogo) {
        this.visitanteLogo = visitanteLogo;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

}
