package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPembelajaranActivity extends AppCompatActivity {

    @BindView(R.id.video_player)
    YouTubePlayerView videoPlayer;
    @BindView(R.id.tv_detail_title_video)
    TextView tv_detail_title_video;
    @BindView(R.id.tv_detail_tgl_video)
    TextView tv_detail_tgl_video;
    @BindView(R.id.tv_detail_isi_video)
    TextView tv_detail_isi_video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_pembelajaran);
        ButterKnife.bind(this);

        getLifecycle().addObserver(videoPlayer);

        videoPlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = getIntent().getStringExtra("embed");
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        tv_detail_title_video.setText(getIntent().getStringExtra("judul"));
        tv_detail_tgl_video.setText(getIntent().getStringExtra("created_at"));
        tv_detail_isi_video.setText(getIntent().getStringExtra("deskripsi"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        videoPlayer.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.release();
    }
}