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

    public static class EmpleadoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Context context;

        TextView textViewEmployeeId;
        TextView textViewFirstName;
        TextView textViewLastName;
        TextView textViewEmail;
        TextView textViewPhoneNumber;
        TextView textViewSalary;
        TextView textViewCommisionPct;
        TextView textViewCreatedBy;
        Button editButton;
        Button borrarButton;



        public EmpleadoViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();
            textViewEmployeeId = itemView.findViewById(R.id.textViewEmployeeId);
            textViewFirstName = itemView.findViewById(R.id.textViewFirstName); // FirstName y LastName en un TextView (textViewName)
            textViewLastName = itemView.findViewById(R.id.textViewLastName); // FirstName y LastName en un TextView (textViewName)
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewSalary = itemView.findViewById(R.id.textViewSalary);
            textViewCommisionPct = itemView.findViewById(R.id.textViewCommisionPct);
            textViewCreatedBy = itemView.findViewById(R.id.textViewEmployeeId);

            editButton = itemView.findViewById(R.id.buttonEditar);
            borrarButton = itemView.findViewById(R.id.buttonBorrar);

        }

        void setOnClickListeners(){

            editButton.setOnClickListener(this);
            borrarButton.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.buttonEditar:
                    String cadena = (String)textViewCreatedBy.getText();
                    if (cadena.equals("grupo_1") ) {

                        Intent intent = new Intent(context,EditarEmpleadoActivity.class);

                        intent.putExtra("textViewEmployeeId",textViewEmployeeId.getText());
                        intent.putExtra("textViewEmployeeId",textViewFirstName.getText());
                        intent.putExtra("textViewEmployeeId",textViewLastName.getText());
                        intent.putExtra("textViewEmployeeId",textViewEmail.getText());
                        intent.putExtra("textViewEmployeeId",textViewPhoneNumber.getText());
                        intent.putExtra("textViewEmployeeId",textViewSalary.getText());
                        intent.putExtra("textViewEmployeeId",textViewCommisionPct.getText());
                        intent.putExtra("textViewEmployeeId",textViewCreatedBy.getText());



                        context.startActivity(intent);
                        break;

                    }else{
                        //FALTA COLOCAR DIALOG
                        Toast.makeText(context,"No es posible editar", Toast.LENGTH_SHORT).show();
                        break;
                    }


                case R.id.buttonBorrar:
                    cadena = (String)textViewCreatedBy.getText();
                    if (cadena.equals("grupo_1") ) {

                        break;

                    }else{
                        //FALTA COLOCAR DIALOG
                        Toast.makeText(context,"No es posible borrar", Toast.LENGTH_SHORT).show();
                        break;
                    }

            }

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

        String textoId= e.getEmployeeId();
        holder.textViewEmployeeId.setText(textoId);
        String textoFirstName = e.getFirstName();
        holder.textViewFirstName.setText(textoFirstName);

        String textoLastName = e.getLastName();
        holder.textViewLastName.setText(textoLastName);

        String textoEmail = e.getEmail();
        holder.textViewEmail.setText(textoEmail);
        String textoPhoneNumber = e.getPhoneNumber();
        holder.textViewPhoneNumber.setText(textoPhoneNumber);
        String textoSalary = Double.toString(e.getSalary());
        holder.textViewSalary.setText(textoSalary);
        String textoCommisionPct = Double.toString(e.getCommisionPct());
        holder.textViewCommisionPct.setText(textoCommisionPct);

        String createdBy = e.getCreatedBy();
        holder.textViewCreatedBy.setText(createdBy);



        holder.setOnClickListeners();



    }

    @Override
    public int getItemCount() {
        // Pasar cuantos elementos tiene nuestra lista:
        return listEmpleados.length;
    }
}
