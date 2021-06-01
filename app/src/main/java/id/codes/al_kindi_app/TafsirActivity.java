package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.FeedAdapter;
import id.codes.al_kindi_app.Adapter.TafsirAdapter;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Tafsir;

public class TafsirActivity extends AppCompatActivity {
    @BindView(R.id.rv_tafsir)
    RecyclerView rv_tafsir;
    TafsirAdapter tafsirAdapter;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tafsir);
        ButterKnife.bind(this);
        reference = FirebaseDatabase.getInstance().getReference().child("tafsir");
        rv_tafsir.setLayoutManager(new GridLayoutManager(this,3));
        FirebaseRecyclerOptions<Tafsir> options
                = new FirebaseRecyclerOptions.Builder<Tafsir>()
                .setQuery(reference, Tafsir.class)
                .build();

        tafsirAdapter = new TafsirAdapter(TafsirActivity.this,options);
        rv_tafsir.setAdapter(tafsirAdapter);
    }
    @Override protected void onStart()
    {
        super.onStart();
        tafsirAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        tafsirAdapter.stopListening();
    }
}