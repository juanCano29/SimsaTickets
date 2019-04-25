package com.example.juankno4.simsaticket.adaptadores;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juankno4.simsaticket.Modelos.AdministrarTec;

import com.example.juankno4.simsaticket.R;

import java.util.List;

public class adaptaProblemaTec extends RecyclerView.Adapter<adaptaProblemaTec.ViewHolder> {
    private List<AdministrarTec> listaproblemas;

    public adaptaProblemaTec(List<AdministrarTec>listaproblemas) {

        this.listaproblemas = listaproblemas;
    }

    @NonNull
    @Override
    public adaptaProblemaTec.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_fragment_seguimiento_tecnico,viewGroup,false);
        return new ViewHolder(vista);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        AdministrarTec problemas = listaproblemas.get(i);
        viewHolder.etipo.setText(problemas.getNombreProblema());
        viewHolder.enombre.setText(problemas.getNomEmp());
        viewHolder.efolio.setText(problemas.getId().toString());
    }

    @Override
    public int getItemCount() {
        return listaproblemas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView efolio,enombre, etipo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            efolio=itemView.findViewById(R.id.efolio);
            enombre=itemView.findViewById(R.id.enombre);
            etipo=itemView.findViewById(R.id.etipo);
        }
    }
}
