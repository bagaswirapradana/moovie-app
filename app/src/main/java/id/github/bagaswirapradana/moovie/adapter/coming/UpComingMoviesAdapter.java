package id.github.bagaswirapradana.moovie.adapter.coming;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import java.util.List;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.UpComingMovieList;
import id.github.bagaswirapradana.moovie.viewholder.UpComingMovieViewHolder;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class UpComingMoviesAdapter extends RecyclerView.Adapter<UpComingMovieViewHolder> implements UpComingMoviesFinder{

    @RootContext
    protected Context context;

    private List<UpComingMovieList> upComingMovieLists;

    @NonNull
    @Override
    public UpComingMovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UpComingMovieViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_upcoming_movie,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingMovieViewHolder holder, int position) {
        if (!upComingMovieLists.isEmpty()){
            holder.bindData(upComingMovieLists.get(position),context);

            holder.itemView.setOnClickListener(v ->
                    DetailMovieActivity_
                    .intent(context)
                    .idMovie(String.valueOf(upComingMovieLists.get(position).getId()))
                    .start());
        }
    }

    @Override
    public int getItemCount() {
        if (upComingMovieLists == null || upComingMovieLists.size() == 0){
            return 0;
        }else {
            return upComingMovieLists.size();
        }
    }

    @Override
    public void bindUpComingMovies(List<UpComingMovieList> upComingMovieLists) {
        this.upComingMovieLists = upComingMovieLists;
    }
}
