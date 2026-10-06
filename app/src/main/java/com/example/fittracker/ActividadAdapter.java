package com.example.fittracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolder> {

    private ArrayList<Actividad> listaActividades;

    public ActividadAdapter(ArrayList<Actividad> listaActividades) {
        this.listaActividades = listaActividades;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTipo;
        TextView txtIntensidad;
        TextView txtAspectos;
        TextView txtEsfuerzo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtIntensidad = itemView.findViewById(R.id.txtIntensidad);
            txtAspectos = itemView.findViewById(R.id.txtAspectos);
            txtEsfuerzo = itemView.findViewById(R.id.txtEsfuerzo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_actividad, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position) {

        Actividad actividad = listaActividades.get(position);

        holder.txtTipo.setText(
                "Entrenamiento: " + actividad.getTipo()
        );

        holder.txtIntensidad.setText(
                "Intensidad: " + actividad.getIntensidad()
        );

        holder.txtAspectos.setText(
                "Completado: " + actividad.getAspectos()
        );

        holder.txtEsfuerzo.setText(
                "Esfuerzo: " + actividad.getEsfuerzo() + " estrellas"
        );
    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }
}