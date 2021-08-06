package id.codes.al_kindi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoaHarian extends AppCompatActivity {
    @BindView(R.id.pdf_doa_harian)
    PDFView pdf_doa_harian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_harian);
        ButterKnife.bind(this);

        pdf_doa_harian.fromAsset("doa_harian.pdf")
                .enableSwipe(true) // allows to block changing pages using swipe
                .swipeHorizontal(true)
                .pageFitPolicy(FitPolicy.BOTH) // mode to fit pages in the view
                .load();


    }
}