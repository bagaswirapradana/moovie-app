package id.github.bagaswirapradana.moovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.activities.detail.DetailMovieActivity_;
import id.github.bagaswirapradana.moovie.model.SimilarMovieList;
import id.github.bagaswirapradana.moovie.viewholder.SimilarMoviesViewHolder;

public class SimilarMoviesAdapter extends RecyclerView.Adapter {

    private List<SimilarMovieList> similarMovieLists;
    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private Context context;

    public SimilarMoviesAdapter(List<SimilarMovieList> similarMovieLists, Context context) {
        this.similarMovieLists = similarMovieLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similar_movie,parent,false);
        viewHolder = new SimilarMoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SimilarMoviesViewHolder){
            final SimilarMovieList similarMovieList = similarMovieLists.get(position);

            String idFilm = String.valueOf(similarMovieList.getId());

            ((SimilarMoviesViewHolder) holder).titleMovie.setText(similarMovieList.getOriginalTitle());

            Picasso.with(context)
                    .load(URL_BASE_IMAGE+similarMovieList.getPosterPath())
                    .into(((SimilarMoviesViewHolder) holder).imageView);

            ((SimilarMoviesViewHolder) holder).itemView.setOnClickListener(v ->
                    DetailMovieActivity_.intent(context).extra("id",idFilm).start());
        }
    }

    @Override
    public int getItemCount() {
        return similarMovieLists.size();
    }
}
