package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2g1h1091.entidades.ResultTrabajo;
import com.example.lab2g1h1091.entidades.Trabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import entidades.Apikey;
import entidades.Departamento;

public class EditarTrabajoActivity extends AppCompatActivity {
    String apiKeyVar;
    String textViewJobId = "";
    String textViewJobTitle = "";
    String textViewMinSalary = "";
    String textViewMaxSalary = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_trabajo);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textViewJobId = extras.getString("textViewJobId");
            textViewJobTitle = extras.getString("textViewJobTitle");
            textViewMinSalary = extras.getString("textViewMinSalary");
            textViewMaxSalary = extras.getString("textViewMaxSalary");
        }

        TextView textViewJobTitleEditar = findViewById(R.id.textViewJobTitleEditar);

        TextView textViewJobTitle_Editar = findViewById(R.id.textViewJob);
        TextView textViewMinSalEditar = findViewById(R.id.textViewMinSal);
        TextView textViewMaxSalEditar = findViewById(R.id.textViewMaxSal);

        textViewJobTitleEditar.setText(textViewJobTitle);

        textViewJobTitle_Editar.setText(textViewJobTitle);
        textViewMinSalEditar.setText(textViewMinSalary);
        textViewMaxSalEditar.setText(textViewMaxSalary);

        //----------------------------------------------------------------------------
        // GetApi
        String url = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/getApiKey?accion=validar";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("resul", response);
                        Gson gson = new Gson();
                        Apikey api = gson.fromJson(response,Apikey.class);
                        apiKeyVar = api.getApikey();
                        /*
                        Intent i = new Intent(MainActivity.this, CrearTrabajoActivity.class);
                        i.putExtra("apikey", apiKeyVar);
                        startActivity(i);
                         */
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
                parametros.put("groupKey","dUSsj7jpKkbK9yADK8Eb");
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);

    }

    public void btnEditarTrabajo(View view) {


        EditText editTextNombre = findViewById(R.id.textViewJob);
        EditText editTextSalarioMin = findViewById(R.id.textViewMinSal);
        EditText editTextSalarioMax = findViewById(R.id.textViewMaxSal);

        final String nombreTrabajo = editTextNombre.getText().toString();
        final String salarioMin = editTextSalarioMin.getText().toString();
        final String salarioMax = editTextSalarioMax.getText().toString();


        //-----------------------------------------------------------------------------
        //GUARDAR TRABAJO
        String url1 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/trabajo";

        StringRequest stringRequest1 = new StringRequest(StringRequest.Method.POST, url1,
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

                parametros.put("jobId",textViewJobId);
                parametros.put("jobTitle",nombreTrabajo);
                parametros.put("minSalary",salarioMin);
                parametros.put("maxSalary",salarioMax);
                parametros.put("update","true");

                return parametros;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                // Pasamos como cabecera el api-key [obtener el api-key desde android]
                cabeceras.put("api-key",apiKeyVar);
                return cabeceras;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest1);

        //-----------------------------------------------------------------------------
        Intent intent2 = new Intent(EditarTrabajoActivity.this,MainActivity.class);
        setResult(RESULT_OK,intent2);
        finish();


    }

}
