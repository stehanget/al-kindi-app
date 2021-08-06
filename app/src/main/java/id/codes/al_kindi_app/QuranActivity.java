package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.FeedAdapter;
import id.codes.al_kindi_app.Adapter.QuranAdapter;
import id.codes.al_kindi_app.Model.Quran;

public class QuranActivity extends AppCompatActivity {
    @BindView(R.id.rv_quran)
    RecyclerView rv_quran;
    @BindView(R.id.btn_playlist)
    ConstraintLayout btn_playlist;
    @BindView(R.id.imageView4)
    ImageView btn_back;
    ArrayList<Quran> listSurat; // Create an ArrayList object
    private static String url = "https://api.npoint.io/99c279bb173a6e28359c/data";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);
        ButterKnife.bind(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_playlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuranActivity.this,PlayListQori.class);
                startActivity(intent);
            }
        });
        listSurat = new ArrayList<Quran>();

        rv_quran = findViewById(R.id.rv_quran); //findId recyclerView yg ada pada activity_read_all.xml

        rv_quran.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv_quran.setLayoutManager(new LinearLayoutManager(this));

        AndroidNetworking.initialize(getApplicationContext()); //inisialisasi FAN
        getData(); // pemanggilan fungsi get data




    }

    private void getData() {
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(QuranActivity.this, "200ok", Toast.LENGTH_SHORT).show();

                        try {
                            for (int i = 0;i<response.length();i++){
                                JSONObject data = response.getJSONObject(i);
                                listSurat.add(new Quran(
                                        data.getString("arti"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("asma"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("audio"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getInt("ayat"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("nama"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                        data.getString("nomor") //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                                ));
                            }
                            QuranAdapter adapter = new QuranAdapter(QuranActivity.this,listSurat);
                            rv_quran.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            Toast.makeText(QuranActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                        }



                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(QuranActivity.this, anError.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}