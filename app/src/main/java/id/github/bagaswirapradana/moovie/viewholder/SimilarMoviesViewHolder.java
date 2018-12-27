package id.github.bagaswirapradana.moovie.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.github.bagaswirapradana.moovie.R;

public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder {

    public TextView titleMovie;

    public ImageView imageView;

    public SimilarMoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        titleMovie = itemView.findViewById(R.id.title);
        imageView = itemView.findViewById(R.id.image);
    }
}
