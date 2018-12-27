package id.github.bagaswirapradana.moovie.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import id.github.bagaswirapradana.moovie.model.TopRatedMovieList;
import id.github.bagaswirapradana.moovie.viewholder.TopRatedMovieViewHolder;

public class TopRatedMoviesAdapter extends RecyclerView.Adapter {

    private List<TopRatedMovieList> topRatedMovieLists;
    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private Context context;

    public TopRatedMoviesAdapter(List<TopRatedMovieList> topRatedMovieLists, Context context) {
        this.topRatedMovieLists = topRatedMovieLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_top_rated_movie,viewGroup,false);
        viewHolder = new TopRatedMovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TopRatedMovieViewHolder){
            TopRatedMovieList topRatedMovieList = topRatedMovieLists.get(i);

            String date = topRatedMovieList.getReleaseDate();
            String idFilm = String.valueOf(topRatedMovieList.getId());

            if (date != null){
                ((TopRatedMovieViewHolder) viewHolder).yearMovie.setText(topRatedMovieList.getReleaseDate().subSequence(0,4));
            }else {
                ((TopRatedMovieViewHolder) viewHolder).yearMovie.setText(topRatedMovieList.getReleaseDate());
            }

            ((TopRatedMovieViewHolder) viewHolder).titleMovie.setText(topRatedMovieList.getOriginalTitle());
            ((TopRatedMovieViewHolder) viewHolder).rateMovie.setText(String.valueOf(topRatedMovieList.getVoteAverage()));
            ((TopRatedMovieViewHolder) viewHolder).ratingBar.setRating((float) ((topRatedMovieList.getVoteAverage()*5)/10));
            ((TopRatedMovieViewHolder) viewHolder).votesMovie.setText(topRatedMovieList.getVoteCount() + " VOTES");
            ((TopRatedMovieViewHolder) viewHolder).itemView.setOnClickListener(v ->
                    DetailMovieActivity_.intent(context).extra("id",idFilm).start());

            Picasso.with(context)
                    .load(URL_BASE_IMAGE+topRatedMovieList.getPosterPath())
                    .into(((TopRatedMovieViewHolder) viewHolder).imageTopRatedMovie);
        }
    }

    @Override
    public int getItemCount() {
        return topRatedMovieLists.size();
    }
}
