package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

public class CrearEmpleadoActivity extends AppCompatActivity {

    String apikey;
    Departamento[] listaDepartamentos;
    Trabajo[] listaTrabajos;
    Empleado[] listaEmpleados;

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
                        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(CrearEmpleadoActivity.this,
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
                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(CrearEmpleadoActivity.this,
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

    public void btnCrearEmpleado(View view){
        EditText textFieldFirstname = findViewById(R.id.textFieldFirstname);
        EditText textFieldLastname = findViewById(R.id.textFieldLastname);
        EditText textFieldEmail = findViewById(R.id.textFieldEmail);
        EditText textFieldCelular = findViewById(R.id.textFieldCelular);
        Spinner spinnerTrabajo = findViewById(R.id.spinnerTrabajo);
        EditText textFieldSalario = findViewById(R.id.textFieldSalario);
        EditText textFieldComissionPct = findViewById(R.id.textFieldComissionPct);
        Spinner spinnerJefe = findViewById(R.id.spinnerJefe);
        Spinner spinnerDepartamentos = findViewById(R.id.spinnerDepartamentos);

        String firstname = textFieldFirstname.getText().toString();
        String lastname = textFieldLastname.getText().toString();
        String email = textFieldEmail.getText().toString();
        String celular = textFieldCelular.getText().toString();
        String trabajoElegido = spinnerTrabajo.getSelectedItem().toString();
        String salario = textFieldSalario.getText().toString();
        String comision = textFieldComissionPct.getText().toString();
        String jefeElegido = spinnerJefe.getSelectedItem().toString();
        String departamento = spinnerDepartamentos.getSelectedItem().toString();


        Departamento[] departamentoElegido = new Departamento[1];

        /*
        Trabajo[] trabajoElegidoArray = new Trabajo[1];
        Empleado[] jefeElegidoArray = new Empleado[1];
        Departamento[] departamentoElegidoArray = new Departamento[1];


        // Obtengo que trabajo, jefe y departamento ha sido elegido, luego obtengo sus otros atributos en un arreglo:
        int i = 0;
        for(Trabajo trabajo:listaTrabajos){
            if(trabajo.getJobTitle() == trabajoElegido){
                trabajoElegidoArray[i] = trabajo;
                break;
            }
            i++;
        }


        i=0;
        for(Empleado empleado:listaEmpleados){
            String nombreCompletoJefe = empleado.getFirstName() + " " + empleado.getLastName();
            if(nombreCompletoJefe == jefeElegido){
                jefeElegidoArray[i] = empleado;
                break;
            }
            i++;
        }


        int i=0;
        for(Departamento departamento:listaDepartamentos){
            if(departamento.getDepartmentName() == departamentoElegido){
                departamentoElegidoArray[i] = departamento;
                break;
            }
            i++;
        }
        */


        int i = 0;
        for(Departamento depa:listaDepartamentos){

            if(depa.getDepartmentName() == departamento) {
                departamentoElegido[i] = depa;
                break;
            }
            i = i +1 ;
        }
        Departamento depart = departamentoElegido[0];


        Log.d("nombreDepartamento",depart.getDepartmentName());
        //Log.d("nombreTrabajo",trabajoElegidoArray[0].getJobTitle());
        //Log.d("nombreJefe",jefeElegidoArray[0].getFirstName());




    }
}
