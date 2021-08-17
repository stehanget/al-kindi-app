package id.codes.al_kindi_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class  QuizActivity extends AppCompatActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_current_question)
    TextView tv_current_question;
    @BindView(R.id.tv_question)
    TextView tv_question;
    @BindView(R.id.tv_option_a)
    TextView tv_option_a;
    @BindView(R.id.tv_option_b)
    TextView tv_option_b;
    @BindView(R.id.tv_option_c)
    TextView tv_option_c;
    @BindView(R.id.tv_option_d)
    TextView tv_option_d;

    @BindView(R.id.btn_confirm)
    Button btn_confirm;
    @BindView(R.id.btn_back)
    Button btn_back;
    @BindView(R.id.btn_option1)
    ConstraintLayout btn_option1;
    @BindView(R.id.btn_option2)
    ConstraintLayout btn_option2;
    @BindView(R.id.btn_option3)
    ConstraintLayout btn_option3;
    @BindView(R.id.btn_option4)
    ConstraintLayout btn_option4;

    @BindView(R.id.img_option1)
    ImageView img_option1;
    @BindView(R.id.img_option2)
    ImageView img_option2;
    @BindView(R.id.img_option3)
    ImageView img_option3;
    @BindView(R.id.img_option4)
    ImageView img_option4;

    private int index = 0;
    private int score = 0;
    String[] question ;
    String[] key ;
    private final String[] answer = new String[10];
    String[][] option;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);

        question = new String[10];
        key = new String[10];
        option = new String[10][4];
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("quiz").child(getIntent().getStringExtra("key")).child("soal");
        SweetAlertDialog sweetAlertDialog = new  SweetAlertDialog(QuizActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog .setTitleText("Menunggu");
        sweetAlertDialog.setContentText("Waiting..");
        sweetAlertDialog.show();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String soal = userSnapshot.child("question").getValue(String.class);
                    String answer_a = userSnapshot.child("answer_a").getValue(String.class);
                    String answer_b = userSnapshot.child("answer_b").getValue(String.class);
                    String answer_c = userSnapshot.child("answer_c").getValue(String.class);
                    String answer_d = userSnapshot.child("answer_d").getValue(String.class);
                    String kunci = userSnapshot.child("key").getValue(String.class);


                    question[count] = soal;
                    key[count] = kunci;
                    option[count][0] = answer_a;
                    option[count][1] = answer_b;
                    option[count][2] = answer_c;
                    option[count][3] = answer_d;

                    count++;
                }
                sweetAlertDialog.dismissWithAnimation();
                _setQuestion();
                btn_back.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError databaseError) {

            }
        });






        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((index < 10) && (answer[index] != null)) {
                    btn_back.setVisibility(View.VISIBLE);

                    if (answer[index].equals(key[index])) score += 10;

                    if (index < 9) {
                        index++;
                        _setQuestion();
                        _setUnselectOption();

                        if (answer[index] != null) _setSelectedOption();

                    } else {
                        new SweetAlertDialog(QuizActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Yeay")
                                .setContentText("Anda mendapat skor "+String.valueOf(score))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        sweetAlertDialog.dismissWithAnimation();
                                        finish();
                                    }
                                })
                                .show();
                    }
                } else {
                    new SweetAlertDialog(QuizActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Pilihlah salah satu jawaban")
                            .show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;

                    if (index == 0) btn_back.setVisibility(View.INVISIBLE);
                    if (answer[index].equals(key[index])) score -= 10;

                    _setQuestion();
                    _setUnselectOption();

                    if (answer[index] != null) _setSelectedOption();
                }
            }
        });
    }

    private void _setUnselectOption() {
        img_option1.setImageResource(R.drawable.img_unselect);
        img_option2.setImageResource(R.drawable.img_unselect);
        img_option3.setImageResource(R.drawable.img_unselect);
        img_option4.setImageResource(R.drawable.img_unselect);
    }

    private void _setSelectedOption() {
        switch (answer[index]) {
            case "a":
                img_option1.setImageResource(R.drawable.img_select);
                break;
            case "b":
                img_option2.setImageResource(R.drawable.img_select);
                break;
            case "c":
                img_option3.setImageResource(R.drawable.img_select);
                break;
            case "d":
                img_option4.setImageResource(R.drawable.img_select);
                break;
        }
    }

    public void _optionSelected(View v) {
        _setUnselectOption();
        switch (v.getId()) {
            case R.id.btn_option1:
                answer[index] = "a";
                img_option1.setImageResource(R.drawable.img_select);
                break;

            case R.id.btn_option2:
                answer[index] = "b";
                img_option2.setImageResource(R.drawable.img_select);
                break;

            case R.id.btn_option3:
                answer[index] = "c";
                img_option3.setImageResource(R.drawable.img_select);
                break;

            case R.id.btn_option4:
                answer[index] = "d";
                img_option4.setImageResource(R.drawable.img_select);
                break;
        }
    }

    private void _setQuestion() {
        tv_current_question.setText(String.valueOf(index + 1));
        tv_question.setText(question[index]);
        tv_option_a.setText(option[index][0]);
        tv_option_b.setText(option[index][1]);
        tv_option_c.setText(option[index][2]);
        tv_option_d.setText(option[index][3]);
    }


    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Oops...")
                .setContentText("Tidak bisa keluar saat Pre Test")
                .show();
    }

}