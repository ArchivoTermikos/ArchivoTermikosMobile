package com.termikos.archivotermikosmobile.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.termikos.archivotermikosmobile.R;

import java.util.List;

public class AulasAdapter extends RecyclerView.Adapter<AulasAdapter.ViewHolder> {
    public List<String> aulas;

    public AulasAdapter(List<String> aulas){
        this.aulas = aulas;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.aulas_recyclerview_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String aula = aulas.get(position);
        holder.nombreAula.setText(aula);
    }

    @Override
    public int getItemCount() {
        return aulas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nombreAula;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreAula = itemView.findViewById(R.id.nombre_aula);
        }
    }
}
