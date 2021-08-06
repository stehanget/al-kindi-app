package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayListQori extends AppCompatActivity {
    @BindView(R.id.btn_nav_surat)
    ConstraintLayout btn_surat;
    @BindView(R.id.btn_playlist_1)
    ImageView btn_playlist_1;
    @BindView(R.id.imageView4)
    ImageView btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_list_qori);
        ButterKnife.bind(this);
        btn_surat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayListQori.this,QuranActivity.class);
                startActivity(intent);
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_playlist_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PlayListQori.this,DetailPlaylist.class);
                startActivity(intent);
            }
        });
    }
}