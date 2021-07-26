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


        Log.i("outside", String.valueOf(datetime));
        return datetime;
    }

}
