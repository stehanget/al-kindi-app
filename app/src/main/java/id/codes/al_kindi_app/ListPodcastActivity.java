package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.AsmaulHusnaAdapter;
import id.codes.al_kindi_app.Adapter.PodcastAdapter;
import id.codes.al_kindi_app.Model.AsmaulHusna;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Podcast;
import id.codes.al_kindi_app.Model.Quran;

public class ListPodcastActivity extends AppCompatActivity {
    @BindView(R.id.rv_podcast)
    RecyclerView rv_podcast;
    ArrayList<Podcast> listPodcast; // Create an ArrayList object
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_podcast);
        ButterKnife.bind(this);
        listPodcast = new ArrayList<>();
        rv_podcast.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
        rv_podcast.setLayoutManager(new GridLayoutManager(this,1));

        databaseReference = FirebaseDatabase.getInstance().getReference("podcast");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Podcast object = ds.getValue(Podcast.class);
                    listPodcast.add(object);
                }
                PodcastAdapter podcastAdapter = new PodcastAdapter(ListPodcastActivity.this,listPodcast);
                rv_podcast.setAdapter(podcastAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



    }
}