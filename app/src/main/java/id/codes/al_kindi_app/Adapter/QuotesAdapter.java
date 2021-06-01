package id.codes.al_kindi_app.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import id.codes.al_kindi_app.Model.Quotes;
import id.codes.al_kindi_app.Model.Tafsir;
import id.codes.al_kindi_app.R;

public class QuotesAdapter extends FirebaseRecyclerAdapter<Quotes, QuotesAdapter.quotesViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public QuotesAdapter(@NonNull FirebaseRecyclerOptions<Quotes> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull QuotesAdapter.quotesViewHolder holder, int position, @NonNull Quotes model) {
        holder.txtQuotes.setText(model.getQuotes());
        holder.txtNama.setText(model.getNama());
        holder.txtLike.setText(String.valueOf(model.getLike()));
    }

    @NonNull
    @Override
    public QuotesAdapter.quotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_feed_quotes, parent, false);
        return new QuotesAdapter.quotesViewHolder(view);
    }

    public class quotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtQuotes,txtNama,txtLike;
        public quotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtQuotes = itemView.findViewById(R.id.textView3);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtLike = itemView.findViewById(R.id.tv_total_like);
        }
    }
}

