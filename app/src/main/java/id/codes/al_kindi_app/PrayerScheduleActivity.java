package id.codes.al_kindi_app;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import id.codes.al_kindi_app.Model.Schedule;

public class PrayerScheduleActivity extends AppCompatActivity {
    String tanggal, tanggalBesok;
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
    @BindView(R.id.tv_countdown_desc)
    TextView tv_countdown_desc;
    @BindView(R.id.imageView4)
    ImageView btn_back;
    String schedule_sholat;

    JSONObject waktu;
    long a = 5000;
    JSONArray datetime;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_schedule);
        ButterKnife.bind(this);
        getData();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        AndroidNetworking.get("https://api.pray.zone/v2/times/today.json?city=Malang").setPriority(Priority.LOW).build().getAsJSONObject(new JSONObjectRequestListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject results = response.getJSONObject("results");
                    datetime = results.getJSONArray("datetime");
                    for (int i = 0 ; i < datetime.length() ; i++){

                        JSONObject data = null;
                        try {
                            data = datetime.getJSONObject(i);

                            waktu = data.getJSONObject("times");
                            JSONObject date = data.getJSONObject("date");
                            tanggal = date.getString("gregorian");

                            SimpleDateFormat formatSholat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date dateImsak = formatSholat.parse(tanggal+" "+waktu.getString("Imsak")+":00");
                            Date dateIsya = formatSholat.parse(tanggal+" "+waktu.getString("Isha")+":00");
                            Date dateFajr = formatSholat.parse(tanggal+" "+waktu.getString("Fajr")+":00");
                            Date dateTerbit = formatSholat.parse(tanggal+" "+waktu.getString("Sunrise")+":00");
                            Date dateDzuhur = formatSholat.parse(tanggal+" "+waktu.getString("Dhuhr")+":00");
                            Date dateAshar = formatSholat.parse(tanggal+" "+waktu.getString("Asr")+":00");
                            Date dateMaghrib = formatSholat.parse(tanggal+" "+waktu.getString("Maghrib")+":00");

                            tv_imsak_time.setText(waktu.getString("Imsak"));
                            tv_shubuh_time.setText(waktu.getString("Fajr"));
                            tv_terbit_time.setText(waktu.getString("Sunrise"));
                            tv_dzuhur_time.setText(waktu.getString("Dhuhr"));
                            tv_ashar_time.setText(waktu.getString("Asr"));
                            tv_maghrib_time.setText(waktu.getString("Maghrib"));
                            tv_isya_time.setText(waktu.getString("Isha"));




                            Calendar c = Calendar.getInstance();
                            c.add(Calendar.HOUR_OF_DAY,-5);
                            long milisDateNow = c.getTimeInMillis() + (5 * 60 * 60 * 1000);
                            long milisDateImsak = dateImsak.getTime();
                            long milisDateIsya = dateIsya.getTime();
                            long milisDateFajr = dateFajr.getTime();
                            long milisDateTerbit = dateTerbit.getTime();
                            long milisDateDzuhur = dateDzuhur.getTime();
                            long milisDateAshar = dateAshar.getTime();
                            long milisDateMaghrib = dateMaghrib.getTime();

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.HOUR_OF_DAY,4);
                            calendar.add(Calendar.DATE,1);
                            long millisImsyaa = calendar.getTimeInMillis();
                            Log.i("test millis",String.valueOf(milisDateNow));
                            if(milisDateNow > milisDateIsya && milisDateNow < millisImsyaa) {
                                tv_countdown_desc.setText("Menjelang Waktu Imsak");
                                schedule_sholat = "Imsak";
                            }else if(milisDateNow > milisDateImsak && milisDateNow < milisDateFajr) {
                                tv_countdown_desc.setText("Menjelang Waktu Shubuh");
                                schedule_sholat = "Fajr";

                            }else if(milisDateNow > milisDateFajr && milisDateNow < milisDateTerbit) {
                                tv_countdown_desc.setText("Menjelang Waktu Terbit");
                                schedule_sholat = "Sunrise";

                            }else if(milisDateNow > milisDateTerbit && milisDateNow < milisDateDzuhur) {
                                tv_countdown_desc.setText("Menjelang Waktu Dhuhur");
                                schedule_sholat = "Dhuhr";

                            }else if(milisDateNow > milisDateDzuhur && milisDateNow < milisDateAshar) {
                                tv_countdown_desc.setText("Menjelang Waktu Ashar");
                                schedule_sholat = "Asr";

                            }else if(milisDateNow > milisDateAshar && milisDateNow < milisDateMaghrib) {
                                tv_countdown_desc.setText("Menjelang Waktu Maghrib");
                                schedule_sholat = "Maghrib";

                            }else if(milisDateNow > milisDateMaghrib && milisDateNow < milisDateIsya) {
                                tv_countdown_desc.setText("Menjelang Waktu Isya");
                                schedule_sholat = "Isha";
                            }
                            else {
                                tv_countdown_desc.setText("Hubungi Admin");
                            }



                        } catch (JSONException | ParseException e) {
                            e.printStackTrace();
                        }

                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String currentDateandTime = sdf.format(new Date());
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                        Date date1 = null;

                        try {
                            date1 = format.parse(currentDateandTime);
                            Date date2 = format.parse(waktu.getString(schedule_sholat)+":00");

                            long mills = date2.getTime() - date1.getTime();

                            if(mills<0)
                            {
                                Date dateMax = format.parse("24:00:00");
                                Date dateMin = format.parse("00:00:00");
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
                                            getData();
                                }
                            }.start();
                        } catch (ParseException e) {
                            e.printStackTrace();
                            Log.i("eror",String.valueOf(e)) ;
                        }
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }

        });

        Log.i("dateTime1", String.valueOf(datetime));


    }

    private void setWaktuSholat (JSONObject waktu) throws JSONException {
        tv_imsak_time.setText(waktu.getString("Imsak"));
        tv_shubuh_time.setText(waktu.getString("Fajr"));
        tv_terbit_time.setText(waktu.getString("Sunrise"));
        tv_dzuhur_time.setText(waktu.getString("Dhuhr"));
        tv_ashar_time.setText(waktu.getString("Asr"));
        tv_maghrib_time.setText(waktu.getString("Maghrib"));
        tv_isya_time.setText(waktu.getString("Isha"));
    }

}