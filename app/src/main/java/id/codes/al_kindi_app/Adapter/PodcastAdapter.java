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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import id.codes.al_kindi_app.Model.AsmaulHusna;
import id.codes.al_kindi_app.Model.Ayat;
import id.codes.al_kindi_app.Model.Podcast;
import id.codes.al_kindi_app.PodcastActivity;
import id.codes.al_kindi_app.R;

public class PodcastAdapter extends RecyclerView.Adapter<PodcastAdapter.PodcastViewHolder> {

    private Context context;
    private ArrayList<Podcast> dataPodcast; //inisialisasi List dengan object DataMahasiswa

    public PodcastAdapter(Context context , ArrayList<Podcast> dataPodcast) {
        this.context = context;
        this.dataPodcast = dataPodcast;
    }

    @NonNull
    @NotNull
    @Override
    public PodcastViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_podcast, parent, false);
        PodcastAdapter.PodcastViewHolder holder = new PodcastAdapter.PodcastViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PodcastViewHolder holder, int position) {
        Podcast podcast = dataPodcast.get(position);
        holder.tv_title.setText(podcast.getNama());
        holder.tv_author.setText(podcast.getPembuat());
        Glide.with(context).load(podcast.getGambar()).into(holder.iv_image_podcast);
        holder.cl_podcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PodcastActivity.class);
                intent.putExtra("gambar",podcast.getGambar());
                intent.putExtra("desc",podcast.getDescription());
                intent.putExtra("author",podcast.getPembuat());
                intent.putExtra("judul",podcast.getNama());
                intent.putExtra("podcast",podcast.getPodcast());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataPodcast.size();
    }

    public class PodcastViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_image_podcast;
        TextView tv_title,tv_author;
        ConstraintLayout cl_podcast;
        public PodcastViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            iv_image_podcast = itemView.findViewById(R.id.iv_podcast);
            cl_podcast = itemView.findViewById(R.id.cl_podcast);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_title = itemView.findViewById(R.id.tv_title_item);
        }
    }
}
