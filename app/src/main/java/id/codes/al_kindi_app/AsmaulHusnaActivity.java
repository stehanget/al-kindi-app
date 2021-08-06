package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.AsmaulHusnaAdapter;
import id.codes.al_kindi_app.Model.AsmaulHusna;
import id.codes.al_kindi_app.Model.Quran;

public class AsmaulHusnaActivity extends AppCompatActivity {
    ArrayList<AsmaulHusna> listAsmaulHusna; // Create an ArrayList object
    @BindView(R.id.rv_asmaul_husna)
    RecyclerView rv_asmaul_husna;
    @BindView(R.id.imageView4)
    ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asmaul_husna);
        ButterKnife.bind(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rv_asmaul_husna = findViewById(R.id.rv_asmaul_husna); //findId recyclerView yg ada pada activity_read_all.xml

        rv_asmaul_husna.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv_asmaul_husna.setLayoutManager(new GridLayoutManager(this,1));
        listAsmaulHusna = new ArrayList<AsmaulHusna>();

        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < obj.length(); i++) {
                JSONObject jo_inside = obj.getJSONObject(i);
                String arab = jo_inside.getString("arab");
                String arti = jo_inside.getString("arti");
                listAsmaulHusna.add(new AsmaulHusna(
                        jo_inside.getInt("urutan"),
                        jo_inside.getString("latin"),
                        jo_inside.getString("arab"), //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                        jo_inside.getString("arti") //"name:/String" diisi sesuai dengan yang di JSON pada read_all.php
                ));
                AsmaulHusnaAdapter asmaulHusnaAdapter = new AsmaulHusnaAdapter(AsmaulHusnaActivity.this,listAsmaulHusna);
                rv_asmaul_husna.setAdapter(asmaulHusnaAdapter);
                


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("asmaul_husna.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}