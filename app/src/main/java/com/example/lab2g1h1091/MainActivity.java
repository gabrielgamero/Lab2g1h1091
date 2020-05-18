package com.example.lab2g1h1091;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    // Inflater de la AppBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Trabajos");
    }

    // Opciones de AppBar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            //case R.id.listarEmpleadosAppBar:
            //    Toast.makeText(this,"listarEmpleadosAppBar",Toast.LENGTH_SHORT).show();
             //   return true;
            case R.id.agregarEmpleadoAppBar:
                Toast.makeText(this,"agregarTrabajoAppBar",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Abrir EmpleadoActivity (asociar en onClick de la vista)
    public void accionListarEmpleadosAppBar(MenuItem item){
        Intent i = new Intent(this,EmpleadoActivity.class);
        startActivity(i);
    }

    public void obtenerDeInternet(View view){
        // URL Web service 2: Listar Trabajos
        String url =
                "http://ec2-54-165-73-192.compute-1.amazonaws.com:9000/listar/trabajos";

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("exitoVolley",response);

                        // Convertir los datos en formato json (response) a una clase en Java con Gson
                        Gson gson = new Gson();
                        DtoTrabajo dtoTrabajo = gson.fromJson(response,DtoTrabajo.class);
                        Log.d("dtoTrabajo.getCuota",Integer.toString(dtoTrabajo.getCuota()));

                        // Obtener la lista de elementos
                        Trabajo[] listaTrabajos = dtoTrabajo.getTrabajos();
                        Log.d("listaTrabajos0",listaTrabajos[0].getJobTitle());

                        // Creamos el Adapter
                        ListaTrabajosAdapter listaTrabajosAdapter =
                                new ListaTrabajosAdapter(listaTrabajos,MainActivity.this);

                        // Obtengamos la vista RecyclerView
                        RecyclerView recyclerView = findViewById(R.id.recyclerView);
                        // Configuramos el adapter al RecyclerView
                        recyclerView.setAdapter(listaTrabajosAdapter);
                        // Configuramos el layoutManager al RecyclerView
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("errorVolley",error.getMessage());
                    }
                }
        ){
            // Sobreescribir getParams() --> Enviar parámetro de body con POST

            // Sobreescribir getHeaders()
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> cabeceras = new HashMap<>();
                // Pasamos como cabecera el api-key [obtener el api-key desde android]
                cabeceras.put("api-key","HTUxbtfKpEb2GJ3Y2d9e");
                return cabeceras;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network networks = connectivityManager.getActiveNetwork();
            if (networks == null) return false;

            NetworkCapabilities networkCapabilities =
                    connectivityManager.getNetworkCapabilities(networks);
            if (networkCapabilities == null) return false;

            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                return true;
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                return true;
            return false;
        } else {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) return false;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE) return true;
            if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) return true;
            return false;
        }
    }
}

