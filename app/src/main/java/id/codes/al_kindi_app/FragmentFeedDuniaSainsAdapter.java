package id.codes.al_kindi_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

public class FragmentFeedDuniaSainsAdapter extends FragmentStateAdapter {
    public FragmentFeedDuniaSainsAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 1 :
                return new BiologiFragment();
            case 2 :
                return new FisikaFragment();
        }

        return new KimiaFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
