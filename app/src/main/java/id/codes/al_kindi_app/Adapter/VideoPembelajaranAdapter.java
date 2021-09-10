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

import java.util.List;

import id.codes.al_kindi_app.DetailMateriActivity;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.VideoPembelajaran;
import id.codes.al_kindi_app.R;
import id.codes.al_kindi_app.VideoPembelajaranActivity;

public class VideoPembelajaranAdapter extends RecyclerView.Adapter<VideoPembelajaranAdapter.VideoViewHolder> {

    private Context context;
    private List<VideoPembelajaran> videoPembelajaranList; //inisialisasi List dengan object DataMahasiswa
    public VideoPembelajaranAdapter(Context context , List<VideoPembelajaran> videoPembelajaranList) {
        this.context = context;
        this.videoPembelajaranList = videoPembelajaranList;
    }

    @NonNull
    @Override
    public VideoPembelajaranAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_feed, parent, false);
        VideoPembelajaranAdapter.VideoViewHolder holder = new VideoPembelajaranAdapter.VideoViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoPembelajaranAdapter.VideoViewHolder holder, int position) {
        VideoPembelajaran video = videoPembelajaranList.get(position);
        holder.tv_title_item.setText(video.getJudul());
        holder.tv_total_like.setText(String.valueOf(video.getLike()));
        holder.tv_post_time.setText(video.getCreated_at());
        Glide.with(context).load(video.getGambar()).into(holder.iv_feed_item);
        holder.cl_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPembelajaranActivity.class);
                intent.putExtra("embed",video.getEmbed());
                intent.putExtra("judul",video.getJudul());
                intent.putExtra("created_at",video.getCreated_at());
                intent.putExtra("deskripsi",video.getContent());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoPembelajaranList.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_feed_item;
        TextView tv_title_item,tv_post_time,tv_total_like,tv_tgl_feed;
        ConstraintLayout cl_feed;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_feed_item = itemView.findViewById(R.id.iv_item_feed);
            tv_title_item = itemView.findViewById(R.id.tv_title_item);
            tv_post_time = itemView.findViewById(R.id.tv_post_time);
            tv_total_like = itemView.findViewById(R.id.tv_total_like);
            cl_feed = itemView.findViewById(R.id.cl_feed);
        }
    }
}
