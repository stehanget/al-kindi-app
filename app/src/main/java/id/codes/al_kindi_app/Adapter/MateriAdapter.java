package id.codes.al_kindi_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import id.codes.al_kindi_app.DetailMateriActivity;
import id.codes.al_kindi_app.FeedDetailActivity;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Quran;
import id.codes.al_kindi_app.R;

public class MateriAdapter extends RecyclerView.Adapter<MateriAdapter.MateriViewHolder>{

    private Context context;
    private List<Feed> feedArrayList; //inisialisasi List dengan object DataMahasiswa
    public MateriAdapter(Context context , List<Feed> feedArrayList) {
        this.context = context;
        this.feedArrayList = feedArrayList;
    }

    @NonNull
    @Override
    public MateriAdapter.MateriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_feed, parent, false);
        MateriAdapter.MateriViewHolder holder = new MateriAdapter.MateriViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MateriAdapter.MateriViewHolder holder, int position) {
        Feed feed = feedArrayList.get(position);
        holder.tv_title_item.setText(feed.getJudul());
        holder.tv_total_like.setText(String.valueOf(feed.getLike()));
        holder.tv_post_time.setText(feed.getCreated_at());
        Glide.with(context).load(feed.getGambar()).into(holder.iv_feed_item);
        holder.cl_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMateriActivity.class);
                intent.putExtra("url",feed.getContent());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedArrayList.size();
    }

    public class MateriViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_feed_item;
        TextView tv_title_item,tv_post_time,tv_total_like,tv_tgl_feed;
        ConstraintLayout cl_feed;
        public MateriViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_feed_item = itemView.findViewById(R.id.iv_item_feed);
            tv_title_item = itemView.findViewById(R.id.tv_title_item);
            tv_post_time = itemView.findViewById(R.id.tv_post_time);
            tv_total_like = itemView.findViewById(R.id.tv_total_like);
            cl_feed = itemView.findViewById(R.id.cl_feed);
        }
    }
}
