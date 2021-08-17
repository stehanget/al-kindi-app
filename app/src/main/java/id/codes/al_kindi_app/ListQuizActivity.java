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
import id.codes.al_kindi_app.Adapter.QuizAdapter;
import id.codes.al_kindi_app.Adapter.QuranAdapter;
import id.codes.al_kindi_app.Model.Feed;
import id.codes.al_kindi_app.Model.Quiz;
import id.codes.al_kindi_app.Model.Quran;

public class ListQuizActivity extends AppCompatActivity {
    @BindView(R.id.rv_feed)
    RecyclerView rv_feed;
    FeedAdapter feedAdapter;
    DatabaseReference reference;
    List<Quiz> quizList;
    @BindView(R.id.imageView4)
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_quiz);
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
        reference = FirebaseDatabase.getInstance().getReference().child(getIntent().getStringExtra("type2"));
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                quizList = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Quiz quiz = ds.getValue(Quiz.class);
                    if (quiz.getJenjang().equals(jenjang)&&quiz.getMapel().equals(mapel)){
                        quizList.add(quiz);
                        quiz.setKey(ds.getKey());
                    }

                }
                rv_feed.setHasFixedSize(true); //agar recyclerView tergambar lebih cepat
                rv_feed.setLayoutManager(new LinearLayoutManager(ListQuizActivity.this));
                QuizAdapter  quizAdapter = new QuizAdapter(ListQuizActivity.this,quizList);
                rv_feed.setAdapter(quizAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}