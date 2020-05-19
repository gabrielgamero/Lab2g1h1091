package com.example.lab2g1h1091;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2g1h1091.entidades.Trabajo;

import org.w3c.dom.Text;

public class ListaTrabajosAdapter extends RecyclerView.Adapter<ListaTrabajosAdapter.TrabajoViewHolder> {

    // Data que obtendré de onResponse
    Trabajo[] listTrabajos;

    // Contexto para inflar el elemento
    Context contexto;

    public ListaTrabajosAdapter(Trabajo[] lista, Context c) {
        this.listTrabajos = lista;
        this.contexto = c;
    }

    public static class TrabajoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //context
        Context context;

        TextView textViewJobTitle;
        TextView textViewJobId;
        TextView textViewMinSalary;
        TextView textViewMaxSalary;
        TextView textViewCreatedBy;
        TextView textViewSalaryRange;

        //buttons
        Button  buttonEditarTrabajo;
        Button buttonBorrarTrabajo;



        public TrabajoViewHolder(View itemView) {
            super(itemView);


            // inicializar contexto
            context = itemView.getContext();

            textViewJobTitle = itemView.findViewById(R.id.textViewJobTitle);
            textViewJobId = itemView.findViewById(R.id.textViewJobId);

            textViewMinSalary = itemView.findViewById(R.id.textViewMinSalary);
            textViewMaxSalary = itemView.findViewById(R.id.textViewMaxSalary);
            textViewCreatedBy = itemView.findViewById(R.id.textViewCreatedBy);
            //Referencia a botones:
            buttonEditarTrabajo = itemView.findViewById(R.id.buttonEditarTrabajo);
            buttonBorrarTrabajo = itemView.findViewById(R.id.buttonBorrarTrabajo);
        }

        void setOnClickListeners(){

                buttonEditarTrabajo.setOnClickListener(this);
                buttonBorrarTrabajo.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonEditarTrabajo:
                    String cadena = (String)textViewCreatedBy.getText();
                    if (cadena.equals("grupo_1") ) {

                    Intent intent = new Intent(context,EditarTrabajoActivity.class);
                    intent.putExtra("textViewJobId",textViewJobId.getText());
                    intent.putExtra("textViewJobTitle",textViewJobTitle.getText());
                    intent.putExtra("textViewMinSalary",textViewMinSalary.getText());
                    intent.putExtra("textViewMaxSalary",textViewMaxSalary.getText());
                    //intent.putExtra("listatrabajos",listTrabajos);
                    context.startActivity(intent);
                    break;

                     }else{
                        //FALTA COLOCAR DIALOG
                        Toast.makeText(context,"No es posible editar", Toast.LENGTH_SHORT).show();
                        break;
                    }


                case R.id.buttonBorrarTrabajo:
                    Intent intent = new Intent(context,BorrarTrabajoActivity.class);
                    intent.putExtra("textViewJobTitle",textViewJobTitle.getText());
                    context.startActivity(intent);
                    break;
            }

        }
    }

    @NonNull
    @Override
    public TrabajoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout del item que se repetirá (y guardarlo en item)
        View item = LayoutInflater.from(contexto).inflate(R.layout.item_rv,parent,false);
        // Creamos el ViewHolder
        TrabajoViewHolder trabajoViewHolder = new TrabajoViewHolder(item);
        // Retornamos el ViewHolder
        return trabajoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajoViewHolder holder, int position) {
        // Obtenemos el Trabajo de la posicion ...
        Trabajo t = listTrabajos[position];
        // Obtenemos los parámetros del trabajo:
        //String texto = Integer.toString(position) + " " + t.getJobTitle();
        String texto = t.getJobTitle();
        holder.textViewJobTitle.setText(texto);

        String textoJobId = t.getJobId();
        holder.textViewJobId.setText(textoJobId);

        String textoMinSalary = Integer.toString(t.getMinSalary());
        holder.textViewMinSalary.setText(textoMinSalary);
    String textoMaxSalary = Integer.toString(t.getMaxSalary());
        holder.textViewMaxSalary.setText(textoMaxSalary);


        String createdBy = t.getCreatedBy();
        holder.textViewCreatedBy.setText(createdBy);
        //String textoSalaryRange = Integer.toString(t.getMinSalary()) + "-" + Integer.toString(t.getMaxSalary());
        //holder.textViewSalaryRange.setText(textoSalaryRange);

        // set events (buttons)
        //holder.buttonBorrarTrabajo.setOnClickListener(this);
        //holder.buttonEditarTrabajo.setOnClickListener(this);


        holder.setOnClickListeners();
    }



    @Override
    public int getItemCount() {
        // Pasar cuantos elementos tiene nuestra lista:
        return listTrabajos.length;
    }
}
