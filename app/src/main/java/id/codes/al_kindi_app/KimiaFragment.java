package id.codes.al_kindi_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.codes.al_kindi_app.Adapter.KimiaAdapter;
import id.codes.al_kindi_app.ui.notifications.NotificationsViewModel;


public class KimiaFragment extends Fragment {
    RecyclerView rv_kimia;
    KimiaAdapter kimiaAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_kimia, container, false);

        ArrayList<String> kimiaItem = new ArrayList<>();
        kimiaItem.add("Materi Kimia 1");
        kimiaItem.add("Materi Kimia 2");
        kimiaItem.add("Materi Kimia 3");
        kimiaItem.add("Materi Kimia 4");
        kimiaItem.add("Materi Kimia 5");
        rv_kimia = root.findViewById(R.id.rv_kimia);
        rv_kimia.setLayoutManager(new LinearLayoutManager(getContext()));
        kimiaAdapter = new KimiaAdapter(getContext(), kimiaItem);
        rv_kimia.setAdapter(kimiaAdapter);
        return root;
    }
}
