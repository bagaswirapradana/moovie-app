package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import id.github.bagaswirapradana.moovie.model.UpComingMovieList;

public class UpComingMovieViewHolder extends RecyclerView.ViewHolder {

    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private ImageView imageUpComingMovie;
    private TextView dateMovie,titleMovie;

    public UpComingMovieViewHolder(@NonNull View itemView) {
        super(itemView);

        imageUpComingMovie = itemView.findViewById(R.id.image);
        dateMovie = itemView.findViewById(R.id.date);
        titleMovie = itemView.findViewById(R.id.title);
    }

    public void bindData(UpComingMovieList upComingMovieList, Context context){
        titleMovie.setText(upComingMovieList.getOriginalTitle());
        dateMovie.setText(upComingMovieList.getReleaseDate());

        Picasso.with(context)
                .load(URL_BASE_IMAGE+upComingMovieList.getPosterPath())
                .into(imageUpComingMovie);
    }
}
