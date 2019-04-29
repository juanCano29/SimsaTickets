package com.example.juankno4.simsaticket.adaptadores;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juankno4.simsaticket.Modelos.HistorialEmp;
import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.Problemas;
import com.example.juankno4.simsaticket.Modelos.TipoProb;
import com.example.juankno4.simsaticket.R;

import java.util.List;

public class adaptaHistR extends RecyclerView.Adapter<adaptaHistR.ViewHolder> {
    private List<HistorialEmp> Listemp;

    public adaptaHistR(List<HistorialEmp> listemp) {
        Listemp = listemp;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartas_historial,
                viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adaptaHistR.ViewHolder viewHolder, int i) {
        viewHolder.Tprob.setText(Listemp.get(i).getNombreProblema());
        viewHolder.est.setText(Listemp.get(i).getEstatus());
        viewHolder.no.setText(Listemp.get(i).getId().toString());
    }

    @Override
    public int getItemCount() {
        return Listemp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tprob,est,no;

        public ViewHolder(View v) {
            super(v);
            Tprob=itemView.findViewById(R.id.tipi);
            est=itemView.findViewById(R.id.esta);
            no=itemView.findViewById(R.id.hrst);
        }
    }
}
