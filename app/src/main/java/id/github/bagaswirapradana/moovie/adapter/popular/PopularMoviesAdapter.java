package id.github.bagaswirapradana.moovie.adapter.popular;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import java.util.List;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.PopularMovieList;
import id.github.bagaswirapradana.moovie.viewholder.PopularMoviesViewHolder;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesViewHolder> implements PopularMoviesFinder {

    @RootContext
    protected Context context;

    private List<PopularMovieList> popularMovieLists;

    @NonNull
    @Override
    public PopularMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PopularMoviesViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_popular_movie,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMoviesViewHolder holder, int position) {
        if (!popularMovieLists.isEmpty()){
            holder.bindData(popularMovieLists.get(position),context);

            holder.itemView.setOnClickListener(v ->
                    DetailMovieActivity_
                    .intent(context)
                    .idMovie(String.valueOf(popularMovieLists.get(position).getId()))
                    .start());
        }
    }

    @Override
    public int getItemCount() {
        if (popularMovieLists == null || popularMovieLists.size() == 0){
            return 0;
        }else {
            return popularMovieLists.size();
        }
    }

    @Override
    public void bindPopularMovies(List<PopularMovieList> movieList) {
        this.popularMovieLists = movieList;
    }
}
