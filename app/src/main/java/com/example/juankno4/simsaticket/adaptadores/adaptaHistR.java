package com.example.juankno4.simsaticket.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juankno4.simsaticket.Modelos.Personas;
import com.example.juankno4.simsaticket.Modelos.Problemas;
import com.example.juankno4.simsaticket.Modelos.TipoProb;
import com.example.juankno4.simsaticket.R;

import java.util.List;

public class adaptaHistR extends RecyclerView.Adapter<adaptaHistR.ViewHolder> {
    private List<TipoProb> ListProb;
    private List<Personas>ListPer;
    private List<Personas>Listtec;

    public adaptaHistR(List<TipoProb> listProb, List<Personas> listPer, List<Personas> listtec) {
        ListProb = listProb;
        ListPer = listPer;
        Listtec = listtec;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartashistr, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptaHistR.ViewHolder viewHolder, int i) {
        viewHolder.tp.setText(ListProb.get(i).getNombreProblema());
        viewHolder.emp.setText(ListPer.get(i).getNomEmp());
        viewHolder.tec.setText(Listtec.get(i).getNomEmp());
    }

    @Override
    public int getItemCount() {
        return Listtec.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tp, tec, emp;

        public ViewHolder(View v) {
            super(v);
            tp=itemView.findViewById(R.id.txt_tipoprob);
            tec=itemView.findViewById(R.id.txt_tecasig);
            emp=itemView.findViewById(R.id.ver);
        }
    }
}
