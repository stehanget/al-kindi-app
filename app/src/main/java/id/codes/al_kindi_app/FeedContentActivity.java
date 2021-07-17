package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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
import id.codes.al_kindi_app.Adapter.QuranAdapter;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Quran;

public class FeedContentActivity extends AppCompatActivity {
    @BindView(R.id.rv_feed)
    RecyclerView rv_feed;
    FeedAdapter feedAdapter;
    DatabaseReference reference;
    List<Feed> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_content);
        ButterKnife.bind(this);



        String jenjang = getIntent().getStringExtra("jenjang2");
        String mapel = getIntent().getStringExtra("mapel2");
//        Toast.makeText(this, jenjang, Toast.LENGTH_SHORT).show();
//        Toast.makeText(this, mapel, Toast.LENGTH_SHORT).show();
        reference = FirebaseDatabase.getInstance().getReference().child("feed");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 arrayList = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Feed feed = ds.getValue(Feed.class);
                    if (feed.getJenjang().equals(jenjang)&&feed.getMapel().equals(mapel)){
                        arrayList.add(feed);
                        Toast.makeText(FeedContentActivity.this, String.valueOf(arrayList), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(FeedContentActivity.this, "no data", Toast.LENGTH_SHORT).show();
                    }

                }
                rv_feed.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
                rv_feed.setLayoutManager(new LinearLayoutManager(FeedContentActivity.this));
               feedAdapter = new FeedAdapter(FeedContentActivity.this,arrayList);
                rv_feed.setAdapter(feedAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}