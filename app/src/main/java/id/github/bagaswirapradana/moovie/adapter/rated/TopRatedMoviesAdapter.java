package id.github.bagaswirapradana.moovie.adapter.rated;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import java.util.List;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.TopRatedMovieList;
import id.github.bagaswirapradana.moovie.viewholder.TopRatedMovieViewHolder;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMovieViewHolder> implements TopRatedMoviesFinder {

    @RootContext
    protected Context context;

    private List<TopRatedMovieList> topRatedMovieLists;

    @NonNull
    @Override
    public TopRatedMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TopRatedMovieViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_top_rated_movie,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopRatedMovieViewHolder holder, int position) {
        if (!topRatedMovieLists.isEmpty()) {
            holder.bindData(topRatedMovieLists.get(position),context);

            holder. itemView.setOnClickListener(v ->  DetailMovieActivity_
                    .intent(context)
                    .idMovie(String.valueOf(topRatedMovieLists.get(position).getId()))
                    .start());
        }
    }

    @Override
    public int getItemCount() {
        if (topRatedMovieLists == null || topRatedMovieLists.size() == 0){
            return 0;
        }else {
            return topRatedMovieLists.size();
        }
    }

    @Override
    public void bindTopRatedMovies(List<TopRatedMovieList> topRatedMovieLists) {
        this.topRatedMovieLists = topRatedMovieLists;
    }
}
