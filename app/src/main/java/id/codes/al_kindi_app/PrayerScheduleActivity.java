package id.codes.al_kindi_app;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    long a = 5000;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_schedule);
        ButterKnife.bind(this);
        getData();
    }

    private void getData() {
        Schedule schedule = new Schedule("https://api.pray.zone/v2/times/today.json?city=Malang");
        JSONArray datetime = schedule.getData();

        Log.i("dateTime1", String.valueOf(datetime));

//        for (int i = 0 ; i < datetime.length() ; i++){
//
//            JSONObject data = null;
//            try {
//                data = datetime.getJSONObject(i);
//
//                JSONObject waktu = data.getJSONObject("times");
//                JSONObject date = data.getJSONObject("date");
//                tanggal = date.getString("gregorian");
//
//                SimpleDateFormat formatSholat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date dateImsak = formatSholat.parse(tanggal+" "+waktu.getString("Imsak")+":00");
//                Date dateIsya = formatSholat.parse(tanggal+" "+waktu.getString("Isha")+":00");
//                Date dateFajr = formatSholat.parse(tanggal+" "+waktu.getString("Fajr")+":00");
//                Date dateTerbit = formatSholat.parse(tanggal+" "+waktu.getString("Sunrise")+":00");
//                Date dateDzuhur = formatSholat.parse(tanggal+" "+waktu.getString("Dhuhr")+":00");
//                Date dateAshar = formatSholat.parse(tanggal+" "+waktu.getString("Asr")+":00");
//                Date dateMaghrib = formatSholat.parse(tanggal+" "+waktu.getString("Maghrib")+":00");
//
//                Calendar c = Calendar.getInstance();
//                long milisDateNow = c.getTimeInMillis() + (5 * 60 * 60 * 1000);
//                long milisDateImsak = dateImsak.getTime();
//                long milisDateIsya = dateIsya.getTime();
//                long milisDateFajr = dateFajr.getTime();
//                long milisDateTerbit = dateTerbit.getTime();
//                long milisDateDzuhur = dateDzuhur.getTime();
//                long milisDateAshar = dateAshar.getTime();
//                long milisDateMaghrib = dateMaghrib.getTime();
//
////                buat thread update milisDateNow
////                jika jam sekarang lebih dari jam 24 maka set isya kemarin
////                jika tidak maka set imsak besok
//
////                if () {
////                    milisDateIsya = milisDateIsyaYesterday;
////                } else {
////                    milisDateImsak = milisDateImsakTomorrow;
////                }
//
//                if(milisDateNow > milisDateIsya && milisDateNow < milisDateImsak) {
//                    tv_countdown_desc.setText("Menjelang Waktu Imsak");
//                }else if(milisDateNow > milisDateImsak && milisDateNow < milisDateFajr) {
//                    tv_countdown_desc.setText("Menjelang Waktu Shubuh");
//                }else if(milisDateNow > milisDateFajr && milisDateNow < milisDateTerbit) {
//                    tv_countdown_desc.setText("Menjelang Waktu Terbit");
//                }else if(milisDateNow > milisDateTerbit && milisDateNow < milisDateDzuhur) {
//                    tv_countdown_desc.setText("Menjelang Waktu Dhuhur");
//                }else if(milisDateNow > milisDateDzuhur && milisDateNow < milisDateAshar) {
//                    tv_countdown_desc.setText("Menjelang Waktu Ashar");
//                }else if(milisDateNow > milisDateAshar && milisDateNow < milisDateMaghrib) {
//                    tv_countdown_desc.setText("Menjelang Waktu Maghrib");
//                }else if(milisDateNow > milisDateMaghrib && milisDateNow < milisDateIsya) {
//                    tv_countdown_desc.setText("Menjelang Waktu Isya");
//                }
//                else {
//                    tv_countdown_desc.setText("Hubungi Admin");
//                }
//
//
//            } catch (JSONException | ParseException e) {
//                e.printStackTrace();
//            }
//
////            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
////            String currentDateandTime = sdf.format(new Date());
////            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
////            Date date1 = null;
////
////            try {
////                date1 = format.parse(currentDateandTime);
////                Date date2 = format.parse(waktu.getString("Imsak")+":00");
////
////                long mills = date2.getTime() - date1.getTime();
////
////                if(mills<0)
////                {
////                    Date dateMax = format.parse("24:00");
////                    Date dateMin = format.parse("00:00");
////                    mills=(dateMax.getTime() -date1.getTime() )+(date2.getTime()-dateMin.getTime());
////                }
////
////                new CountDownTimer(mills, 1000) {
////                    public void onTick(long millisUntilFinished) {
////                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
////                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
////                                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
////                        tv_countdown.setText(hms);
////                    }
////                    public void onFinish() {
//////                                            getData();
////                    }
////                }.start();
////            } catch (ParseException e) {
////                e.printStackTrace();
////            }
//        }
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