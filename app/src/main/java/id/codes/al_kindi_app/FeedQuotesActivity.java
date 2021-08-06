package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.QuotesAdapter;
import id.codes.al_kindi_app.Adapter.TafsirAdapter;
import id.codes.al_kindi_app.Model.Quotes;
import id.codes.al_kindi_app.Model.Tafsir;

public class FeedQuotesActivity extends AppCompatActivity {
    @BindView(R.id.rv_feed_quotes)
    RecyclerView rv_feed_quotes;
    QuotesAdapter quotesAdapter;
    DatabaseReference reference;
    @BindView(R.id.imageView4)
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_quotes);
        ButterKnife.bind(this);
        reference = FirebaseDatabase.getInstance().getReference().child("quotes");
        rv_feed_quotes.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Quotes> options
                = new FirebaseRecyclerOptions.Builder<Quotes>()
                .setQuery(reference, Quotes.class)
                .build();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        quotesAdapter = new QuotesAdapter(options);
        rv_feed_quotes.setAdapter(quotesAdapter);

    }
    @Override protected void onStart()
    {
        super.onStart();
        quotesAdapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        quotesAdapter.stopListening();
    }
}