package id.github.bagaswirapradana.moovie.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import id.github.bagaswirapradana.moovie.R;

public class TopRatedMovieViewHolder extends RecyclerView.ViewHolder {

    public TextView titleMovie, yearMovie, rateMovie, votesMovie;

    public RatingBar ratingBar;

    public ImageView imageTopRatedMovie;

    public TopRatedMovieViewHolder(@NonNull View itemView) {
        super(itemView);

        imageTopRatedMovie = itemView.findViewById(R.id.image);
        titleMovie = itemView.findViewById(R.id.title);
        rateMovie = itemView.findViewById(R.id.rate);
        votesMovie = itemView.findViewById(R.id.votes);
        yearMovie = itemView.findViewById(R.id.year);
        ratingBar = itemView.findViewById(R.id.rating);
    }
}
