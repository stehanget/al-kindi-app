package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        ButterKnife.bind(this);
        String jenjang = getIntent().getStringExtra("jenjang");
        Toast.makeText(this, jenjang, Toast.LENGTH_SHORT).show();
        btn_biologi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapelActivity.this,FeedContentActivity.class);
                intent.putExtra("jenjang2",jenjang);
                intent.putExtra("mapel2","biologi");
                startActivity(intent);
            }
        });
    }
}