package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);
        ButterKnife.bind(this);
        tv_detail_title_feed.setText(getIntent().getStringExtra("judul"));
        tv_detail_tgl_feed.setText(getIntent().getStringExtra("tanggal"));

//        Quotes quotes = ((Quotes)getApplicationContext());
//        quotes.getLike();
//        Toast.makeText(quotes, String.valueOf(quotes.getLike()), Toast.LENGTH_SHORT).show();

    }
}