package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BorrarTrabajoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_trabajo);

        String textViewJobTitle = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textViewJobTitle = extras.getString("textViewJobTitle");
        }

        TextView textViewJobTitleBorrar = findViewById(R.id.textViewJobTitleBorrar);
        textViewJobTitleBorrar.setText(textViewJobTitle);
    }

}
