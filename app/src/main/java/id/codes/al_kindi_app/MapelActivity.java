package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapelActivity extends AppCompatActivity {
    @BindView(R.id.btn_biologi)
    CardView btn_biologi;
    @BindView(R.id.btn_kimia)
    CardView btn_kimia;
    @BindView(R.id.btn_fisika)
    CardView btn_fisika;
    @BindView(R.id.txt_menu_materi_biologi)
    TextView txt_menu_materi_biologi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        ButterKnife.bind(this);
        String type = getIntent().getStringExtra("type");
        String jenjang = getIntent().getStringExtra("jenjang");
        if (jenjang.equals("Ibtidaiyah")){
            btn_fisika.setVisibility(View.INVISIBLE);
            btn_kimia.setVisibility(View.INVISIBLE);
            txt_menu_materi_biologi.setText("IPA");
            btn_biologi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                    intent.putExtra("jenjang2", jenjang);
                    intent.putExtra("mapel2", "ipa");
                    intent.putExtra("type2", type);
                    startActivity(intent);
                }
            });
        }else {
            btn_biologi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                    intent.putExtra("jenjang2", jenjang);
                    intent.putExtra("mapel2", "biologi");
                    intent.putExtra("type2", type);
                    startActivity(intent);
                }
            });

            btn_kimia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                    intent.putExtra("jenjang2", jenjang);
                    intent.putExtra("mapel2", "kimia");
                    intent.putExtra("type2", type);
                    startActivity(intent);
                }
            });
            btn_fisika.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                    intent.putExtra("jenjang2", jenjang);
                    intent.putExtra("mapel2", "fisika");
                    intent.putExtra("type2", type);
                    startActivity(intent);
                }
            });
        }
    }
}