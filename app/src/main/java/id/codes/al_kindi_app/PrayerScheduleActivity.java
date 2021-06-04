package id.codes.al_kindi_app;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PrayerScheduleActivity extends AppCompatActivity {
    String tanggal,tanggalBesok;
    String url = "https://api.pray.zone/v2/times/today.json?city=Malang";
    String url2 = "https://api.pray.zone/v2/times/day.json?city=malang&date=";
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
    @BindView(R.id.tv_countdown)
    TextView tv_countdown;
    @RequiresApi(api = Build.VERSION_CODES.O)
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
                    @RequiresApi(api = Build.VERSION_CODES.O)
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

                                JSONObject date = data.getJSONObject("date");
                                tanggal = date.getString("gregorian");
                                String tanggalFix = null;

                                SimpleDateFormat formatTanggal = new SimpleDateFormat("yyyy-MM-dd");
                                Calendar c = Calendar.getInstance();
                                try {
                                    c.setTime(formatTanggal.parse(tanggal));
                                    c.add(Calendar.DATE, 1);  // number of days to add
                                    tanggalFix = formatTanggal.format(c.getTime());  // dt is now the new date
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                SimpleDateFormat formatSholat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date dateImsak = formatSholat.parse(tanggalFix+" "+waktu.getString("Imsak")+":00");
                                Date dateIsya = formatSholat.parse(tanggal+" "+waktu.getString("Isha")+":00");
                                SimpleDateFormat formatTanggalSekarang = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                String formatSekarang = formatTanggalSekarang.format(new Date());
                                Date dateNow = formatTanggalSekarang.parse(formatSekarang);


                                if(dateNow.before(dateImsak) && dateNow.after(dateIsya)){
                                    Toast.makeText(PrayerScheduleActivity.this, String.valueOf(dateImsak)+"\n"+String.valueOf(dateNow), Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(PrayerScheduleActivity.this, "false", Toast.LENGTH_SHORT).show();
                                }

                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                                String currentDateandTime = sdf.format(new Date());
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                                Date date1 = null;
                                try {
                                    date1 = format.parse(currentDateandTime);
                                    Date date2 = format.parse(waktu.getString("Imsak")+":00");

                                    long mills = date2.getTime() - date1.getTime();

                                    if(mills<0)
                                    {
                                        Date dateMax = format.parse("24:00");
                                        Date dateMin = format.parse("00:00");
                                        mills=(dateMax.getTime() -date1.getTime() )+(date2.getTime()-dateMin.getTime());
                                    }


                                    new CountDownTimer(mills, 1000) {

                                        public void onTick(long millisUntilFinished) {
                                            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
                                            tv_countdown.setText(hms);
                                        }

                                        public void onFinish() {

                                        }
                                    }.start();


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }






                            }



                        } catch (JSONException | ParseException e) {
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