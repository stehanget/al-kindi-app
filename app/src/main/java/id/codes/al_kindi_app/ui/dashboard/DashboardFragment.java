package id.codes.al_kindi_app.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import id.codes.al_kindi_app.BiologiFragment;
import id.codes.al_kindi_app.MapelActivity;
import id.codes.al_kindi_app.Model.User;
import id.codes.al_kindi_app.R;

public class DashboardFragment extends Fragment {

    ConstraintLayout btn_materi;
        CardView btn_menu_literasi,btn_menu_ilmuan;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String jenjang ="";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        btn_materi = root.findViewById(R.id.btn_materi);
        btn_menu_ilmuan = root.findViewById(R.id.btn_menu_ilmuan);
        btn_menu_literasi = root.findViewById(R.id.btn_menu_literasi);

        btn_materi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseUser = firebaseAuth.getInstance().getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        jenjang = user.getJenjang();
                        Intent intent = new Intent(getContext(), MapelActivity.class);
                        intent.putExtra("jenjang", jenjang);
                        intent.putExtra("type","feed");
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

        btn_menu_literasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUser = firebaseAuth.getInstance().getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        jenjang = user.getJenjang();
                        Intent intent = new Intent(getContext(), MapelActivity.class);
                        intent.putExtra("jenjang", jenjang);
                        intent.putExtra("type","literasi");
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        btn_menu_ilmuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseUser = firebaseAuth.getInstance().getCurrentUser();
                databaseReference = FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        jenjang = user.getJenjang();
                        Intent intent = new Intent(getContext(), MapelActivity.class);
                        intent.putExtra("jenjang", jenjang);
                        intent.putExtra("type","scientist");
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        return root;
    }
}