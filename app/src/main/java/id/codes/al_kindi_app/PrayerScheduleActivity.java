package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.codes.al_kindi_app.Adapter.AyatAdapter;
import id.codes.al_kindi_app.Model.Ayat;

public class PrayerScheduleActivity extends AppCompatActivity {

    String url = "https://api.pray.zone/v2/times/today.json?city=Malang";
    @BindView(R.id.tv_imsak_time)
    TextView tv_imsak_time;
    @BindView(R.id.tv_shubuh_time)
    TextView tv_shubuh_time;
    @BindView(R.id.tv_terbit_time)
    TextView tv_terbit_time;
    @BindView(R.id.tv_dzuhur_time)
    TextView tv_dzuhur_time;
    @BindView(R.id.tv_ashar_time)
    TextView tv_ashar_time;
    @BindView(R.id.tv_maghrib_time)
    TextView tv_maghrib_time;
    @BindView(R.id.tv_isya_time)
    TextView tv_isya_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_schedule);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        AndroidNetworking.get(url)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject results =  response.getJSONObject("results");
                            JSONArray datetime =  results.getJSONArray("datetime");
                            for (int i = 0 ; i < datetime.length() ; i++){
                                JSONObject data = datetime.getJSONObject(i);
                                JSONObject waktu = data.getJSONObject("times");
                                tv_imsak_time.setText(waktu.getString("Imsak"));
                                tv_shubuh_time.setText(waktu.getString("Fajr"));
                                tv_terbit_time.setText(waktu.getString("Sunrise"));
                                tv_dzuhur_time.setText(waktu.getString("Dhuhr"));
                                tv_ashar_time.setText(waktu.getString("Asr"));
                                tv_maghrib_time.setText(waktu.getString("Maghrib"));
                                tv_isya_time.setText(waktu.getString("Isha"));


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PrayerScheduleActivity.this, String.valueOf(e), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }
}