package com.example.lab2g1h1091;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class EditarTrabajoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_trabajo);

        String textViewJobTitle = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            textViewJobTitle = extras.getString("textViewJobTitle");
        }

        TextView textViewJobTitleEditar = findViewById(R.id.textViewJobTitleEditar);
        textViewJobTitleEditar.setText(textViewJobTitle);
    }
}
