package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2g1h1091.entidades.ResultTrabajo;
import com.example.lab2g1h1091.entidades.Trabajo;
import com.google.gson.Gson;


import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Apikey;
import entidades.Departamento;
import entidades.DtoDepartamento;

public class CrearTrabajoActivity extends AppCompatActivity {

    String apikey;
    Departamento[] listaDepartamentos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_trabajo);

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
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearTrabajoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item,listaDepartNames);
                        Spinner spinner = findViewById(R.id.spinnerDepart);
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


    public void btnCrearTrabajo(View view) {
        Spinner editTextDepart = findViewById(R.id.spinnerDepart);
        EditText editTextAbre = findViewById(R.id.TextAbrev);
        EditText editTextNombre = findViewById(R.id.TextNombre);
        EditText editTextSalarioMin = findViewById(R.id.TextSalarioMin);
        EditText editTextSalarioMax = findViewById(R.id.TextSalarioMax);

        String abreviacion = editTextAbre.getText().toString();
        String departamento = editTextDepart.getSelectedItem().toString();
        final String nombreTrabajo = editTextNombre.getText().toString();
        final String salarioMin = editTextSalarioMin.getText().toString();
        final String salarioMax = editTextSalarioMax.getText().toString();

        Departamento[] departamentoElegido = new Departamento[1];
        //------------------------------------------------------------------------------------
        //obtener el objeto departamento
        int i = 0;
        for(Departamento depa:listaDepartamentos){

            if(depa.getDepartmentName() == departamento) {
                departamentoElegido[i] = depa;
                break;
            }
            i = i +1 ;
        }
        Departamento depart = departamentoElegido[0];
        final String jobId = depart.getDepartmentShortName() + "_"+ abreviacion;

        Intent intent = getIntent();
        Trabajo[] listaTrabajos = (Trabajo[]) intent.getSerializableExtra("lista_trabajos");
       //flag verifica si jobid es unico
        int flag = 0;
        for(Trabajo trabajo:listaTrabajos){

            if(trabajo.getJobId().contains(jobId)) {
                flag=2;
                break;
            }

        }
        Intent intent3 = getIntent();
        apikey = intent3.getStringExtra("apikey");

        //------------------------------------------------------------------------------------




        if (flag != 0) {
             Toast.makeText(this, "JobId ya existe, ingrese otra Abreviacion", Toast.LENGTH_LONG).show();


        } else {
            //-----------------------------------------------------------------------------
            //GUARDAR TRABAJO
            String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/trabajo";

            StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("resul_trabajo", response);
                            //Log.d("resul_trabajo", error);

                            Gson gson = new Gson();
                            ResultTrabajo estado_Trabajo = gson.fromJson(response,ResultTrabajo.class);


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("errorVol", error.getMessage());
                        }
                    }) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<>();
                    //parametros.put("api-key",apikey);
                    parametros.put("jobId",jobId);
                    parametros.put("jobTitle",nombreTrabajo);
                    parametros.put("minSalary",salarioMin);
                    parametros.put("maxSalary",salarioMax);
                    parametros.put("createdBy","grupo1");
                    return parametros;
                }
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String,String> cabeceras = new HashMap<>();
                    // Pasamos como cabecera el api-key [obtener el api-key desde android]
                    cabeceras.put("api-key",apikey);
                    return cabeceras;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);

            requestQueue.add(stringRequest);

            //-----------------------------------------------------------------------------

                Intent intent2 = new Intent(CrearTrabajoActivity.this,MainActivity.class);
                setResult(RESULT_OK,intent2);
                finish();


        }

    }
}
