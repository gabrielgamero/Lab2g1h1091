package com.example.lab2g1h1091.entidades;

public class DtoTrabajo {

    private String estado;
    private String cuota;
    private Trabajo[] lista;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCuota() {
        return cuota;
    }

    public void setCuota(String cuota) {
        this.cuota = cuota;
    }

    public Trabajo[] getLista() {
        return lista;
    }

    public void setLista(Trabajo[] lista) {
        this.lista = lista;
    }
}
