package com.example.lab2g1h1091.entidades;

public class DtoTrabajo {

    // Todos los nombres de atributos deben coincidir con su NOMBRE y TIPO en JSON
    private String estado;
    private int cuota;
    private Trabajo[] trabajos;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCuota() {
        return cuota;
    }

    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public Trabajo[] getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(Trabajo[] trabajos) {
        this.trabajos = trabajos;
    }
}
