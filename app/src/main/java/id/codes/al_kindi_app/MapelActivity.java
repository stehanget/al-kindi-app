package id.codes.al_kindi_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
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
    @BindView(R.id.txt_menu_materi_biologi)
    TextView txt_menu_materi_biologi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapel);
        ButterKnife.bind(this);
        String type = getIntent().getStringExtra("type");
        String jenjang = getIntent().getStringExtra("jenjang");


        if (!type.equals("quiz")) {
            if (jenjang.equals("Ibtidaiyah")) {
                btn_fisika.setVisibility(View.INVISIBLE);
                btn_kimia.setVisibility(View.INVISIBLE);
                txt_menu_materi_biologi.setText("IPA");
                btn_biologi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "ipa");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });
            } else {
                btn_biologi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "biologi");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });

                btn_kimia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "kimia");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });
                btn_fisika.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, FeedContentActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "fisika");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        } else {
            if (jenjang.equals("Ibtidaiyah")) {
                btn_fisika.setVisibility(View.INVISIBLE);
                btn_kimia.setVisibility(View.INVISIBLE);
                txt_menu_materi_biologi.setText("IPA");
                btn_biologi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, ListQuizActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "ipa");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });
            } else {
                Toast.makeText(this, "quiz", Toast.LENGTH_SHORT).show();
                btn_biologi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, ListQuizActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "biologi");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });

                btn_kimia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, ListQuizActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "kimia");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });




                    }
                });
                btn_fisika.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MapelActivity.this);
                        LayoutInflater inflater = MapelActivity.this.getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.dialog_kelas, null);
                        builder1.setView(dialogView);

                        Spinner spinner = dialogView.findViewById(R.id.spinner_kelas);
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(MapelActivity.this, android.R.layout.simple_spinner_item, getKelas(jenjang));
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        spinner.setAdapter(spinnerArrayAdapter);


                        builder1.setCancelable(true);
                        AlertDialog alert11 = builder1.create();
                        alert11.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        alert11.show();

                        ConstraintLayout btn_next_mapel = dialogView.findViewById(R.id.btn_next_mapel);
                        btn_next_mapel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String kelas = spinner.getSelectedItem().toString();
                                Intent intent = new Intent(MapelActivity.this, ListQuizActivity.class);
                                intent.putExtra("jenjang2", jenjang);
                                intent.putExtra("mapel2", "fisika");
                                intent.putExtra("kelas2", kelas);
                                intent.putExtra("type2", type);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        }
    }

    private String[] getKelas(String jenjang) {
        String kelas[] = new String[7];
        if (jenjang.equals("Ibtidaiyah")){
            kelas = new String[]{"1", "2", "3", "4", "5", "6"};
        }
        else if (jenjang.equals("Tsanawiyah")){
            kelas = new String[]{"7", "8", "9"};
        }
        else if (jenjang.equals("Aliyah")){
            kelas = new String[]{"10","11","12"};
        }

        return kelas;
    }
}
