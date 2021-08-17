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

import java.util.List;

import id.codes.al_kindi_app.FeedDetailActivity;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Quiz;
import id.codes.al_kindi_app.QuizActivity;
import id.codes.al_kindi_app.R;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder> {

    private Context context;
    private List<Quiz> quizList; //inisialisasi List dengan object DataMahasiswa
    public QuizAdapter(Context context , List<Quiz> quizList) {
        this.context = context;
        this.quizList = quizList;
    }

    @NonNull
    @NotNull
    @Override
    public QuizAdapter.QuizViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_feed, parent, false);
        QuizAdapter.QuizViewHolder holder = new QuizAdapter.QuizViewHolder(v); //inisialisasi ViewHolder
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull QuizAdapter.QuizViewHolder holder, int position) {
        Quiz quiz = quizList.get(position);
        holder.tv_title_item.setText(quiz.getJudul());
        holder.tv_post_time.setText(quiz.getCreated_at());
        Glide.with(context).load(quiz.getGambar()).into(holder.iv_feed_item);
        holder.cl_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("judul",quiz.getKey());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_feed_item;
        TextView tv_title_item,tv_post_time,tv_total_like,tv_tgl_feed;
        ConstraintLayout cl_feed;
        public QuizViewHolder

                (@NonNull View itemView) {
            super(itemView);
            iv_feed_item = itemView.findViewById(R.id.iv_item_feed);
            tv_title_item = itemView.findViewById(R.id.tv_title_item);
            tv_post_time = itemView.findViewById(R.id.tv_post_time);
            tv_total_like = itemView.findViewById(R.id.tv_total_like);
            cl_feed = itemView.findViewById(R.id.cl_feed);
        }
    }
}
