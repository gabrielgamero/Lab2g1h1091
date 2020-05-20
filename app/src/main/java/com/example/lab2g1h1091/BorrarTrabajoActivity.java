package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lab2g1h1091.entidades.EstadoBorrar;
import com.example.lab2g1h1091.entidades.ResultTrabajo;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import entidades.Apikey;

public class BorrarTrabajoActivity extends AppCompatActivity {
    String apiKeyVar;
    String textViewJobId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_trabajo);

        final String[] apix = {"asd"};
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textViewJobId = extras.getString("textViewJobId");
        }

        //TextView textViewJobTitleBorrar = findViewById(R.id.textViewJobTitleBorrar);
        //textViewJobTitleBorrar.setText(textViewJobTitle);




//-----------------------------------------------------------------------------------------------------------
        //-----------------------------------------------------------------------------
        //GUARDAR TRABAJO
        String url1 = "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/borrar/trabajo?id="+textViewJobId;

        final String finalTextViewJobId = textViewJobId;
            StringRequest stringRequest1 = new StringRequest(StringRequest.Method.DELETE, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("resul_trabajo", response);
                        Log.d("resul_trabajo1", textViewJobId);

                        //Log.d("resul_trabajo", error);

                        Gson gson = new Gson();
                        EstadoBorrar borrartrabajo = gson.fromJson(response, EstadoBorrar.class);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorVol", error.getMessage());
                    }
                }) {

/*
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                parametros.put("id", "AD_ME");

               // parametros.put("api-key", "HTUxbtfKpEb2GJ3Y2d9e");

                return parametros;
            } */

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> cabeceras = new HashMap<>();
                // Pasamos como cabecera el api-key [obtener el api-key desde android]
                cabeceras.put("api-key", "HTUxbtfKpEb2GJ3Y2d9e");


                return cabeceras;
            }

        };

        RequestQueue requestQueue1 = Volley.newRequestQueue(this);

        requestQueue1.add(stringRequest1);

        //-----------------------------------------------------------------------------
        Intent intent2 = new Intent(BorrarTrabajoActivity.this, MainActivity.class);
        setResult(RESULT_OK, intent2);
        finish();


    }

}
