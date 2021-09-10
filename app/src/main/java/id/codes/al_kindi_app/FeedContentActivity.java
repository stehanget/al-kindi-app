package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.FeedAdapter;
import id.codes.al_kindi_app.Adapter.MateriAdapter;
import id.codes.al_kindi_app.Adapter.QuranAdapter;
import id.codes.al_kindi_app.Adapter.VideoPembelajaranAdapter;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Quran;
import id.codes.al_kindi_app.Model.VideoPembelajaran;

public class FeedContentActivity extends AppCompatActivity {
    @BindView(R.id.rv_feed)
    RecyclerView rv_feed;
    FeedAdapter feedAdapter;
    DatabaseReference reference;
    List<Feed> arrayList;
    @BindView(R.id.imageView4)
    ImageView btn_back;
    List <VideoPembelajaran> videoPembelajaranList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);
        ButterKnife.bind(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        String jenjang = getIntent().getStringExtra("jenjang2");
        String mapel = getIntent().getStringExtra("mapel2");
//        Toast.makeText(this, jenjang, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, mapel, Toast.LENGTH_SHORT).show();

        if (getIntent().getStringExtra("type2").equals("video")){
            reference = FirebaseDatabase.getInstance().getReference().child(getIntent().getStringExtra("type2"));
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    videoPembelajaranList = new ArrayList<>();
                    for(DataSnapshot ds : snapshot.getChildren()) {
                        VideoPembelajaran video = ds.getValue(VideoPembelajaran.class);


                        if (video.getJenjang().equals(jenjang)&&video.getMapel().equals(mapel)&&video.getKelas().equals(getIntent().getStringExtra("kelas2"))){
                            videoPembelajaranList.add(video);
                        }else {
                            Toast.makeText(FeedContentActivity.this, "else", Toast.LENGTH_SHORT).show();
                        }

                    }
                    rv_feed.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
                    rv_feed.setLayoutManager(new LinearLayoutManager(FeedContentActivity.this));
                    VideoPembelajaranAdapter videoPembelajaranAdapter = new VideoPembelajaranAdapter(FeedContentActivity.this,videoPembelajaranList);
                    rv_feed.setAdapter(videoPembelajaranAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else {
            reference = FirebaseDatabase.getInstance().getReference().child(getIntent().getStringExtra("type2"));
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    arrayList = new ArrayList<>();
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Feed feed = ds.getValue(Feed.class);
                        if (feed.getJenjang().equals(jenjang) && feed.getMapel().equals(mapel) && feed.getKelas().equals(getIntent().getStringExtra("kelas2"))) {
                            arrayList.add(feed);
                        } else {
                        }

                    }
                    rv_feed.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
                    rv_feed.setLayoutManager(new LinearLayoutManager(FeedContentActivity.this));
                    if (getIntent().getStringExtra("type2").equals("feed")) {
                        MateriAdapter materiAdapter = new MateriAdapter(FeedContentActivity.this, arrayList);
                        rv_feed.setAdapter(materiAdapter);
                    } else {
                        feedAdapter = new FeedAdapter(FeedContentActivity.this, arrayList);
                        rv_feed.setAdapter(feedAdapter);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }



    }

}