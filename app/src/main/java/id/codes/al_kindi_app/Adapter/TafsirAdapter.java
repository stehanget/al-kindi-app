package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import id.codes.al_kindi_app.DetailTafsirActivity;
import id.codes.al_kindi_app.Model.Tafsir;
import id.codes.al_kindi_app.R;

public class TafsirAdapter extends FirebaseRecyclerAdapter<Tafsir, TafsirAdapter.tafsirViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    Context context;
    public TafsirAdapter(Context context, @NonNull FirebaseRecyclerOptions<Tafsir> options) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull tafsirViewHolder holder, int position, @NonNull Tafsir model) {
        Glide.with(context).load(model.getGambar()).into(holder.img_item);
        holder.tv_item.setText(model.getNama());
        holder.ll_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailTafsirActivity.class);
                intent.putExtra("url",model.getPdf());
                context.startActivity(intent);
            }
        });


    }

    @NonNull
    @Override
    public tafsirViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_tafsir, parent, false);
        return new TafsirAdapter.tafsirViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();

    }

    public class tafsirViewHolder extends RecyclerView.ViewHolder {
        ImageView img_item;
        TextView tv_item;
        LinearLayout ll_content;
        public tafsirViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item = itemView.findViewById(R.id.img_item);
            tv_item = itemView.findViewById(R.id.tv_item);
            ll_content = itemView.findViewById(R.id.ll_content);
        }
    }
}
