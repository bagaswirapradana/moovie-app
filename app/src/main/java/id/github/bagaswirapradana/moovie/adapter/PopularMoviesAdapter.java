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
import id.github.bagaswirapradana.moovie.model.PopularMovieList;
import id.github.bagaswirapradana.moovie.viewholder.PopularMoviesViewHolder;

public class PopularMoviesAdapter extends RecyclerView.Adapter {

    private List<PopularMovieList> popularMovieLists;
    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private Context context;

    public PopularMoviesAdapter(List<PopularMovieList> popularMovieLists, Context context) {
        this.popularMovieLists = popularMovieLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_popular_movie,viewGroup,false);
        viewHolder = new PopularMoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PopularMoviesViewHolder){
            final PopularMovieList popularMovieList = popularMovieLists.get(i);

            String idFilm = String.valueOf(popularMovieList.getId());

            ((PopularMoviesViewHolder) viewHolder).textView.setText(popularMovieList.getOriginalTitle());

            ((PopularMoviesViewHolder) viewHolder).itemView.setOnClickListener(v ->
                DetailMovieActivity_.intent(context).extra("id",idFilm).start());

            Picasso.with(context)
                    .load(URL_BASE_IMAGE+popularMovieList.getPosterPath())
                    .into(((PopularMoviesViewHolder) viewHolder).imageView);
        }
    }

    @Override
    public int getItemCount() {
        return popularMovieLists.size();
    }
}
