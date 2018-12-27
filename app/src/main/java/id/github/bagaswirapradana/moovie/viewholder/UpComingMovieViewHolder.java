package id.github.bagaswirapradana.moovie.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import id.github.bagaswirapradana.moovie.R;

public class UpComingMovieViewHolder extends RecyclerView.ViewHolder {

    public ImageView imageUpComingMovie;

    public TextView dateMovie,titleMovie;


    public UpComingMovieViewHolder(@NonNull View itemView) {
        super(itemView);

        imageUpComingMovie = itemView.findViewById(R.id.image);
        dateMovie = itemView.findViewById(R.id.date);
        titleMovie = itemView.findViewById(R.id.title);
    }
}
