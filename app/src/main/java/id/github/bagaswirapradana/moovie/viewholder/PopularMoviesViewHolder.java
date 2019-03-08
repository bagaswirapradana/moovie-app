package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.PopularMovieList;

public class PopularMoviesViewHolder extends RecyclerView.ViewHolder {


    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";

    private ImageView imageView;
    private TextView textView;

    public PopularMoviesViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.image);
        textView = itemView.findViewById(R.id.title);
    }

    public void bindData(PopularMovieList popularMovie, Context context){
        textView.setText(popularMovie.getOriginalTitle());

        Picasso.with(context)
                .load(URL_BASE_IMAGE+popularMovie.getPosterPath())
                .into(imageView);
    }
}
