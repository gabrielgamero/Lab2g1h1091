package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2g1h1091.entidades.DtoTrabajo;
import com.example.lab2g1h1091.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import entidades.Departamento;
import entidades.DtoDepartamento;

public class ListarTrabajosSpinner extends AppCompatActivity {

    String apikey;
    Trabajo[] listaTrabajos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_trabajos_spinner);

        Intent intent = getIntent();
        apikey = intent.getStringExtra("apikey");

        String url =
                "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

        StringRequest trabajoRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVol", response);
                        //Log.d("apikey", apikey);

                        Gson gson = new Gson();
                        DtoTrabajo dtoTrabajo = gson.fromJson(response, DtoTrabajo.class);
                        listaTrabajos = dtoTrabajo.getTrabajos();
                        String[] listaTrabajoNames = new String[listaTrabajos.length];
                        int i = 0;
                        for (Trabajo trabajo : listaTrabajos) {
                            listaTrabajoNames[i] = trabajo.getJobTitle();
                            i = i + 1;
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, listaTrabajoNames);
                        Spinner spinner = findViewById(R.id.spinnerTrabajo);
                        spinner.setAdapter(adapter2);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorVol", error.getMessage());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<>();
                cabeceras.put("api-key", apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(trabajoRequest);


    }

}