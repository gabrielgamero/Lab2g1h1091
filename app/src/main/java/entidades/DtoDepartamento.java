package entidades;

public class DtoDepartamento {

    private String estado;
    private String cuota;

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

    public Departamento[] getLista() {
        return lista;
    }

    public void setLista(Departamento[] lista) {
        this.lista = lista;
    }

    private Departamento[] lista;


}
