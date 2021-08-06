package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Model.Quotes;

public class FeedDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_detail_title_feed)
    TextView tv_detail_title_feed;
    @BindView(R.id.tv_detail_tgl_feed)
    TextView tv_detail_tgl_feed;
    @BindView(R.id.tv_detail_isi_feed)
    TextView tv_detail_isi_feed;
    @BindView(R.id.img_detail_content)
    ImageView img_detail_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        ButterKnife.bind(this);
        tv_detail_title_feed.setText(getIntent().getStringExtra("judul"));
        tv_detail_tgl_feed.setText(getIntent().getStringExtra("created_at"));
        Glide.with(this).load(getIntent().getStringExtra("gambar")).into(img_detail_content);
        tv_detail_isi_feed.setText(getIntent().getStringExtra("content"));
    }
}