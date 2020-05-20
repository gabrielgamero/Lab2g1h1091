package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import entidades.Departamento;
import entidades.DtoDepartamento;

public class CrearEmpleadoActivity extends AppCompatActivity {

    String apikey;
    Departamento[] listaDepartamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_empleado);

        Intent intent = getIntent();
        apikey = intent.getStringExtra("apikey");

        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/departamentos";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVol", response);
                        //Log.d("apikey", apikey);

                        Gson gson = new Gson();
                        DtoDepartamento dtoDepartamento = gson.fromJson(response,DtoDepartamento.class);
                        listaDepartamentos = dtoDepartamento.getDepartamentos();
                        String[] listaDepartNames = new String[listaDepartamentos.length];
                        int i = 0;
                        for(Departamento depa:listaDepartamentos){
                            listaDepartNames[i] = depa.getDepartmentName();
                            i = i +1 ;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearEmpleadoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,listaDepartNames);
                        Spinner spinner = findViewById(R.id.spinnerDepartamentos);
                        spinner.setAdapter(adapter);


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
                Map<String,String> cabeceras = new HashMap<>();
                cabeceras.put("api-key",apikey);
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);




    }
}
