package com.example.juankno4.simsaticket.adaptadores;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.juankno4.simsaticket.Modelos.HistorialRoot;
import com.example.juankno4.simsaticket.R;

import java.util.List;

public class adaptadorHR extends RecyclerView.Adapter<adaptadorHR.ViewHolder> {
    private List<HistorialRoot> listHis;

    public adaptadorHR(List<HistorialRoot> listHis) {
        this.listHis = listHis;
    }

    @NonNull
    @Override
    public adaptadorHR.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cartashistr,
                viewGroup, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adaptadorHR.ViewHolder viewHolder, int i) {
        viewHolder.Tipop.setText(listHis.get(i).getTp());
        viewHolder.tec.setText(listHis.get(i).getTecnico());
        viewHolder.emp.setText(listHis.get(i).getEmpleado());
        viewHolder.estate.setText(listHis.get(i).getEstatus());
    }

    @Override
    public int getItemCount() {
        return listHis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tipop,tec,emp,estate;

        public ViewHolder(View v) {
            super(v);
            Tipop=itemView.findViewById(R.id.txt_tipoprob);
            tec=itemView.findViewById(R.id.txt_tecasig);
            emp=itemView.findViewById(R.id.ver);
            estate=itemView.findViewById(R.id.est);
        }
    }
}
