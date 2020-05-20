package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2g1h1091.entidades.DtoEmpleado;
import com.example.lab2g1h1091.entidades.DtoTrabajo;
import com.example.lab2g1h1091.entidades.Empleado;
import com.example.lab2g1h1091.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import entidades.Departamento;
import entidades.DtoDepartamento;

public class EditarEmpleadoActivity extends AppCompatActivity {

    String apikey;
    Departamento[] listaDepartamentos;
    Trabajo[] listaTrabajos;
    Empleado[] listaEmpleados;
    Empleado empleado;
    String textViewEmployeeId;
    String textViewFirstName;
    String textViewLastName;
    String textViewEmail;
    String textViewPhoneNumber;
    String textViewSalary;
    String textViewCommisionPct;
    String textViewCreatedBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_empleado);

        Intent intent = getIntent();
        apikey = intent.getStringExtra("apikey");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textViewEmployeeId = extras.getString("textViewJobId");
            textViewFirstName = extras.getString("textViewJobTitle");
            textViewLastName = extras.getString("textViewMinSalary");
            textViewEmail = extras.getString("textViewMaxSalary");
            textViewPhoneNumber = extras.getString("textViewMaxSalary");
            textViewSalary = extras.getString("textViewMaxSalary");
            textViewCommisionPct = extras.getString("textViewMaxSalary");
            textViewCreatedBy = extras.getString("textViewMaxSalary");

        }




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
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditarEmpleadoActivity.this,
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


        String url2 =
                "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

        StringRequest trabajoRequest = new StringRequest(StringRequest.Method.GET, url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVol", response);
                        //Log.d("apikey", apikey);

                        Gson gson = new Gson();
                        DtoTrabajo dtoTrabajo = gson.fromJson(response, DtoTrabajo.class);
                        listaTrabajos = dtoTrabajo.getTrabajos();
                        String[] listaTrabajoNames = new String[listaTrabajos.length];
                        int t = 0;
                        for (Trabajo trabajo : listaTrabajos) {
                            listaTrabajoNames[t] = trabajo.getJobTitle();
                            t = t + 1;
                        }
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(EditarEmpleadoActivity.this,
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


        String url3 =
                "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/empleados";

        StringRequest empleadoRequest = new StringRequest(StringRequest.Method.GET, url3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVol", response);
                        //Log.d("apikey", apikey);

                        Gson gson = new Gson();
                        DtoEmpleado dtoEmpleado = gson.fromJson(response, DtoEmpleado.class);
                        listaEmpleados = dtoEmpleado.getEmpleados();
                        String[] listaEmpleadosNames = new String[listaEmpleados.length];
                        int h = 0;
                        for (Empleado empleado : listaEmpleados) {
                            listaEmpleadosNames[h] = empleado.getFirstName() + " " + empleado.getLastName();
                            h = h + 1;
                        }
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(EditarEmpleadoActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, listaEmpleadosNames);
                        Spinner spinner = findViewById(R.id.spinnerJefe);
                        spinner.setAdapter(adapter3);


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

        requestQueue.add(stringRequest);
        requestQueue.add(trabajoRequest);
        requestQueue.add(empleadoRequest);


    }
}
