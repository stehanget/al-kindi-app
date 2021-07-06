package id.codes.al_kindi_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.codes.al_kindi_app.Adapter.KimiaAdapter;


public class BiologiFragment extends Fragment {

    RecyclerView rv_kimia;
    KimiaAdapter kimiaAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_biologi, container, false);
        ArrayList<String> kimiaItem = new ArrayList<>();
        kimiaItem.add("Materi Biologi 1");
        kimiaItem.add("Materi Biologi 2");
        kimiaItem.add("Materi Biologi 3");
        kimiaItem.add("Materi Biologi 4");
        kimiaItem.add("Materi Biologi 5");
        rv_kimia = root.findViewById(R.id.rv_kimia);
        rv_kimia.setLayoutManager(new LinearLayoutManager(getContext()));
        kimiaAdapter = new KimiaAdapter(getContext(), kimiaItem);
        rv_kimia.setAdapter(kimiaAdapter);
        return root;
    }
}