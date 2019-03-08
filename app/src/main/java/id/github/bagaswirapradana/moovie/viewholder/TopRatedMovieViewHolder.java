package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.TopRatedMovieList;

public class TopRatedMovieViewHolder extends RecyclerView.ViewHolder {

    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private TextView titleMovie, yearMovie, rateMovie, votesMovie;
    private RatingBar ratingBar;
    private ImageView imageTopRatedMovie;

    public TopRatedMovieViewHolder(@NonNull View itemView) {
        super(itemView);

        imageTopRatedMovie = itemView.findViewById(R.id.image);
        titleMovie = itemView.findViewById(R.id.title);
        rateMovie = itemView.findViewById(R.id.rate);
        votesMovie = itemView.findViewById(R.id.votes);
        yearMovie = itemView.findViewById(R.id.year);
        ratingBar = itemView.findViewById(R.id.rating);
    }

    public void bindData(TopRatedMovieList topRatedMovieList, Context context){
        titleMovie.setText(topRatedMovieList.getOriginalTitle());
        rateMovie.setText(String.valueOf(topRatedMovieList.getVoteAverage()));
        votesMovie.setText(topRatedMovieList.getVoteCount() + " VOTES");
        ratingBar.setRating((float) (topRatedMovieList.getVoteAverage()*5/10));

        if (topRatedMovieList.getReleaseDate().isEmpty()){
            yearMovie.setText(topRatedMovieList.getReleaseDate());
        }else {
            yearMovie.setText(topRatedMovieList.getReleaseDate().subSequence(0,4));
        }

        Picasso.with(context)
                .load(URL_BASE_IMAGE+topRatedMovieList.getPosterPath())
                .into(imageTopRatedMovie);
    }
}
