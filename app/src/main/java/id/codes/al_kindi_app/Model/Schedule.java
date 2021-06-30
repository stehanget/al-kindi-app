package id.codes.al_kindi_app.Model;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Schedule {
    String url;
    JSONArray datetime;

    public Schedule(String url) {
        this.url = url;
    }

    public JSONArray getData () {
        AndroidNetworking.get(url).setPriority(Priority.LOW).build().getAsJSONObject(new JSONObjectRequestListener() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject results = response.getJSONObject("results");
                    datetime = results.getJSONArray("datetime");
                    Log.i("onResponse", String.valueOf(datetime));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(ANError anError) {

            }

        });

        Log.i("outside", String.valueOf(datetime));
        return datetime;
    }

}
