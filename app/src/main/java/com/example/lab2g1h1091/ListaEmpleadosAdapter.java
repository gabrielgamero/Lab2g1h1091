package com.example.lab2g1h1091;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2g1h1091.entidades.Empleado;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {

    // Data que obtendré de onResponse
    Empleado[] listEmpleados;

    // Contexto para inflar el elemento
    Context contexto;

    public ListaEmpleadosAdapter(Empleado[] lista, Context c) {
        this.listEmpleados = lista;
        this.contexto = c;
    }

    public static class EmpleadoViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewEmail;
        TextView textViewPhoneNumber;
        TextView textViewSalary;
        TextView textViewCommisionPct;

        public EmpleadoViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName); // FirstName y LastName en un TextView (textViewName)
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewSalary = itemView.findViewById(R.id.textViewSalary);
            textViewCommisionPct = itemView.findViewById(R.id.textViewCommisionPct);
        }
    }

    @NonNull
    @Override
    public ListaEmpleadosAdapter.EmpleadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el layout del item que se repetirá (y guardarlo en item)
        View item = LayoutInflater.from(contexto).inflate(R.layout.item_rv_empleado,parent,false);
        // Creamos el ViewHolder
        ListaEmpleadosAdapter.EmpleadoViewHolder empleadoViewHolder = new ListaEmpleadosAdapter.EmpleadoViewHolder(item);
        // Retornamos el ViewHolder
        return empleadoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEmpleadosAdapter.EmpleadoViewHolder holder, int position) {
        // Obtenemos el Empleado de la posicion ...
        Empleado e = listEmpleados[position];
        // Obtenemos los parámetros del Empleado:
        String textoName = e.getFirstName() + " " + e.getLastName(); // FirstName y LastName en un TextView (textViewName)
        holder.textViewName.setText(textoName);
        String textoEmail = "Email: " + e.getEmail();
        holder.textViewEmail.setText(textoEmail);
        String textoPhoneNumber = e.getPhoneNumber();
        holder.textViewPhoneNumber.setText(textoPhoneNumber);
        String textoSalary = "Salario: " + e.getSalary();
        holder.textViewSalary.setText(textoSalary);
        String textoCommisionPct = "ComissionPct: " +  Double.toString(e.getCommisionPct());
        holder.textViewCommisionPct.setText(textoCommisionPct);
    }

    @Override
    public int getItemCount() {
        // Pasar cuantos elementos tiene nuestra lista:
        return listEmpleados.length;
    }
}
