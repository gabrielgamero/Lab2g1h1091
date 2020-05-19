package entidades;

import com.google.gson.annotations.SerializedName;

import java.text.StringCharacterIterator;

public class Apikey<key> {
    private String estado;
    private String cuota;
    @SerializedName(value = "api-key")
    private String apikey;


    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }





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





}
