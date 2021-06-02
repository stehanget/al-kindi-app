package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.codes.al_kindi_app.Model.AsmaulHusna;
import id.codes.al_kindi_app.Model.Quran;
import id.codes.al_kindi_app.QuranContentActivity;
import id.codes.al_kindi_app.R;

public class AsmaulHusnaAdapter extends RecyclerView.Adapter<AsmaulHusnaAdapter.asmaulHusnaViewHolder> {
    private Context context;
    private ArrayList<AsmaulHusna> dataAsmaulHusna; //inisialisasi List dengan object DataMahasiswa
    public AsmaulHusnaAdapter(Context context , ArrayList<AsmaulHusna> dataAsmaulHusna) {
        this.context = context;
        this.dataAsmaulHusna = dataAsmaulHusna;
    }

    @NonNull
    @Override
    public asmaulHusnaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_asmaul_husna, parent, false);
        AsmaulHusnaAdapter.asmaulHusnaViewHolder holder = new AsmaulHusnaAdapter.asmaulHusnaViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull asmaulHusnaViewHolder holder, int position) {
        AsmaulHusna asmaulHusna = dataAsmaulHusna.get(position);
        holder.tv_arab.setText(asmaulHusna.getArab());
        holder.tv_arti.setText(asmaulHusna.getArti());
    }

    @Override
    public int getItemCount() {
        return dataAsmaulHusna.size();
    }

    public class asmaulHusnaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_arab,tv_arti;
        public asmaulHusnaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_arab = itemView.findViewById(R.id.tv_arab);
            tv_arti = itemView.findViewById(R.id.tv_arti);
        }
    }
}
