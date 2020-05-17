package com.example.lab2g1h1091;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public static class TrabajoViewHolder extends RecyclerView.ViewHolder{
        TextView textViewJobTitle;

        public TrabajoViewHolder(View itemView) {
            super(itemView);
            textViewJobTitle = itemView.findViewById(R.id.textViewJobTitle);
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
        String texto = Integer.toString(position) + " " + t.getJobTitle();
        holder.textViewJobTitle.setText(texto);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
