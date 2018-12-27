package id.github.bagaswirapradana.moovie.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.github.bagaswirapradana.moovie.R;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    public TextView judul,overview;

    public ImageView poster,backdrop;

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        judul = itemView.findViewById(R.id.title);
        overview = itemView.findViewById(R.id.overview);
        poster = itemView.findViewById(R.id.poster);
        backdrop = itemView.findViewById(R.id.backdrop);
    }
}
