package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PodcastActivity extends AppCompatActivity {
    @BindView(R.id.img_podcast)
    ImageView img_podcast;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_desc)
    TextView tv_desc;
    @BindView(R.id.tv_author)
    TextView tv_author;
    @BindView(R.id.img_play)
    ImageView img_play;
    @BindView(R.id.img_stop)
    ImageView img_stop;
    MediaPlayer mediaPlayer;
    @BindView(R.id.imageView4)
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        ButterKnife.bind(this);

        Glide.with(this).load(getIntent().getStringExtra("gambar"))
                .into(img_podcast);
        tv_nama.setText(getIntent().getStringExtra("judul"));
        tv_desc.setText(getIntent().getStringExtra("desc"));
        tv_author.setText("Oleh : "+getIntent().getStringExtra("author"));

        img_stop.setVisibility(View.GONE);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.release();
                finish();
            }
        });

        mediaPlayer = new MediaPlayer();
        String link = getIntent().getStringExtra("podcast"); // your URL here
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(link);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)

        } catch (IOException e) {
            e.printStackTrace();
        }


        img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_play.setVisibility(View.GONE);
                img_stop.setVisibility(View.VISIBLE);
                mediaPlayer.start();

            }
        });

        img_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_play.setVisibility(View.VISIBLE);
                img_stop.setVisibility(View.GONE);
                mediaPlayer.pause();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.release();
        finish();
    }
}