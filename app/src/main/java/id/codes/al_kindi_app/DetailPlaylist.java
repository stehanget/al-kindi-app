package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPlaylist extends AppCompatActivity {
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
        setContentView(R.layout.activity_detail_playlist);
        ButterKnife.bind(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_stop.setVisibility(View.GONE);

        mediaPlayer = new MediaPlayer();
        String link = "https://files-islamdownload-2ae88485.nos.jkt-1.neo.id/ID/123862/v2/001_Al-Fatihah.mp3"; // your URL here
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
}