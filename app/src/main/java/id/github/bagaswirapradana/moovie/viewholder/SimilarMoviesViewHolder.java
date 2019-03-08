package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.SimilarMovieList;

public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder {

    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private TextView titleMovie;
    private ImageView imageView;

    public SimilarMoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        titleMovie = itemView.findViewById(R.id.title);
        imageView = itemView.findViewById(R.id.image);
    }

    public void bindData(SimilarMovieList similarMovieList, Context context){
        titleMovie.setText(similarMovieList.getOriginalTitle());

        Picasso.with(context)
                .load(URL_BASE_IMAGE+similarMovieList.getPosterPath())
                .into(imageView);
    }
}
