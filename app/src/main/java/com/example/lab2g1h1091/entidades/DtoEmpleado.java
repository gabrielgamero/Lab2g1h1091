package com.example.lab2g1h1091.entidades;

public class DtoEmpleado {

    // Todos los nombres de atributos deben coincidir con su NOMBRE y TIPO en JSON
    private String estado;
    private int cuota;
    private Empleado[] empleados;

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

    public Empleado[] getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado[] empleados) {
        this.empleados = empleados;
    }
}
