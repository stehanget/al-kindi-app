package id.codes.al_kindi_app.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import id.codes.al_kindi_app.AsmaulHusnaActivity;
import id.codes.al_kindi_app.FeedContentActivity;
import id.codes.al_kindi_app.FeedDuniaSainsActivity;
import id.codes.al_kindi_app.FeedQuotesActivity;
import id.codes.al_kindi_app.PrayerScheduleActivity;
import id.codes.al_kindi_app.QiblaDirectionActivity;
import id.codes.al_kindi_app.QuranActivity;
import id.codes.al_kindi_app.R;
import id.codes.al_kindi_app.TafsirActivity;

public class HomeFragment extends Fragment {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    private HomeViewModel homeViewModel;
    TextView tv_location_time;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        tv_location_time = root.findViewById(R.id.tv_location_time);

        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PackageManager.PERMISSION_GRANTED);
        } else {
            getCurrentLocation();
        }

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
                Intent intent = new Intent(getContext(), FeedDuniaSainsActivity.class);
                startActivity(intent);
            }
        });
        btn_menu_waktu_sholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PrayerScheduleActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(getContext(), AsmaulHusnaActivity.class);
                startActivity(intent);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_LOCATION_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(getActivity(), "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.getFusedLocationProviderClient(requireActivity())
                .requestLocationUpdates(locationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(@NonNull @NotNull LocationResult locationResult) {
                        super.onLocationResult(locationResult);
                        LocationServices.getFusedLocationProviderClient(requireActivity())
                                .removeLocationUpdates(this);
                        if (locationResult != null && locationResult.getLocations().size() > 0) {
                            int latestLocaionIndex = locationResult.getLocations().size() - 1;
                            double latitude =
                                    locationResult.getLocations().get(latestLocaionIndex).getLatitude();
                            double longitude =
                                    locationResult.getLocations().get(latestLocaionIndex).getLongitude();

                            Location location = new Location("providerNA");
                            location.setLatitude(latitude);
                            location.setLongitude(longitude);

                            Geocoder geocoder = new Geocoder(requireActivity(), Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                String cityName = addresses.get(0).getLocality();

                                SimpleDateFormat formatCurrentDate = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                                String currentDate = formatCurrentDate.format(new Date());

                                tv_location_time.setText(String.format("%s, %s", cityName, currentDate));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, Looper.getMainLooper());
    }
}