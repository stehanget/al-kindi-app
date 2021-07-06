package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.codes.al_kindi_app.FeedContentActivity;
import id.codes.al_kindi_app.FeedDetailActivity;
import id.codes.al_kindi_app.KimiaFragment;
import id.codes.al_kindi_app.R;

public class KimiaAdapter  extends RecyclerView.Adapter<KimiaAdapter.KimiaViewHolder> {

    ArrayList<String>data;
    Context context;

    public KimiaAdapter(Context context, ArrayList<String> kimiaItem) {
        this.context = context;
        this.data = kimiaItem;
    }

    @NonNull
    @Override
    public KimiaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kimia, parent, false);
        KimiaAdapter.KimiaViewHolder holder = new KimiaAdapter.KimiaViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull KimiaViewHolder holder, int position) {
        holder.txt_kimia_materi.setText(data.get(position));
        holder.cl_kimia_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FeedContentActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class KimiaViewHolder extends RecyclerView.ViewHolder {
        TextView txt_kimia_materi;
        ConstraintLayout cl_kimia_materi;
        public KimiaViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_kimia_materi = itemView.findViewById(R.id.txt_kimia_materi);
            cl_kimia_materi  = itemView.findViewById(R.id.cl_kimia_item);
        }
    }
}
