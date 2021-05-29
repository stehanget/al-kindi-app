package id.codes.al_kindi_app.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

import id.codes.al_kindi_app.FeedContentActivity;
import id.codes.al_kindi_app.FeedQuotesActivity;
import id.codes.al_kindi_app.QiblaDirectionActivity;
import id.codes.al_kindi_app.QuranActivity;
import id.codes.al_kindi_app.R;
import id.codes.al_kindi_app.TafsirActivity;

import static androidx.core.content.ContextCompat.getSystemService;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        // method to get the location

        ConstraintLayout btn_menu_quran = root.findViewById(R.id.btn_menu_1);
        ConstraintLayout btn_menu_dunia_sains = root.findViewById(R.id.btn_menu_2);
        ConstraintLayout btn_menu_waktu_sholat = root.findViewById(R.id.btn_menu_3);
        ConstraintLayout btn_menu_arah_kiblat = root.findViewById(R.id.btn_menu_4);
        ConstraintLayout btn_menu_tafsir_ = root.findViewById(R.id.btn_menu_5);
        ConstraintLayout btn_menu_doa = root.findViewById(R.id.btn_menu_6);
        ConstraintLayout btn_menu_asmaul_husna = root.findViewById(R.id.btn_menu_7);
        ConstraintLayout btn_menu_quotes = root.findViewById(R.id.btn_menu_8);

        btn_menu_quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuranActivity.class);
                startActivity(intent);
            }
        });
        btn_menu_dunia_sains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedContentActivity.class);
                startActivity(intent);
            }
        });
        btn_menu_waktu_sholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), FeedContentActivity.class);
//                startActivity(intent);
            }
        });
        btn_menu_arah_kiblat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QiblaDirectionActivity.class);
                startActivity(intent);
            }
        });
        btn_menu_tafsir_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TafsirActivity.class);
                startActivity(intent);
            }
        });
        btn_menu_doa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TafsirActivity.class);
//                startActivity(intent);
            }
        });
        btn_menu_asmaul_husna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TafsirActivity.class);
//                startActivity(intent);
            }
        });
        btn_menu_quotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FeedQuotesActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}