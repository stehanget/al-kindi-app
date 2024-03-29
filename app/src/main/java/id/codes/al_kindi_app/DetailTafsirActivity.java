package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTafsirActivity extends AppCompatActivity {
    @BindView(R.id.pdf_tafsir)
    PDFView pdf_tafsir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tafsir);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");
        getPdf(url);
    }
    private void getPdf(String url) {
        new AsyncTask<Void, Void, Void>() {
            @SuppressLint("WrongThread")
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    InputStream input = new URL(url).openStream();
                    pdf_tafsir.fromStream(input)
                            .enableSwipe(true) // allows to block changing pages using swipe
                            .pageFling(true)
                            .pageFitPolicy(FitPolicy.BOTH)
                             .load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}