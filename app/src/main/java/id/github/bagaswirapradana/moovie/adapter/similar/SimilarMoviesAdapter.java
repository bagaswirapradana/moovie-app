package id.github.bagaswirapradana.moovie.adapter.similar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.SimilarMovieList;
import id.github.bagaswirapradana.moovie.viewholder.SimilarMoviesViewHolder;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesViewHolder> implements SimilarMoviesFinder{

    @RootContext
    protected Context context;

    private List<SimilarMovieList> similarMovieLists;

    @NonNull
    @Override
    public SimilarMoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new SimilarMoviesViewHolder(LayoutInflater
               .from(context)
               .inflate(R.layout.item_similar_movie,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarMoviesViewHolder holder, int position) {
        if (!similarMovieLists.isEmpty()){
            holder.bindData(similarMovieLists.get(position),context);

            holder.itemView.setOnClickListener(v ->
                    DetailMovieActivity_
                    .intent(context)
                    .idMovie(String.valueOf(similarMovieLists.get(position).getId()))
                    .start());
        }
    }

    @Override
    public int getItemCount() {
        if (similarMovieLists == null || similarMovieLists.size() == 0){
            return 0;
        }else {
            return similarMovieLists.size();
        }
    }

    @Override
    public void bindSimilarMovies(List<SimilarMovieList> similarMovieLists) {
        this.similarMovieLists = similarMovieLists;
    }
}
