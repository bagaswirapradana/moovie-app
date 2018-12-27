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
import id.github.bagaswirapradana.moovie.model.UpComingMovieList;
import id.github.bagaswirapradana.moovie.viewholder.UpComingMovieViewHolder;

public class UpComingMoviesAdapter extends RecyclerView.Adapter {

    private List<UpComingMovieList> upComingMovieLists;
    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private Context context;

    public UpComingMoviesAdapter(List<UpComingMovieList> upComingMovieLists, Context context) {
        this.upComingMovieLists = upComingMovieLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_upcoming_movie,viewGroup,false);
        viewHolder = new UpComingMovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof UpComingMovieViewHolder){
            UpComingMovieList upComingMovieList = upComingMovieLists.get(i);

            String idFilm = String.valueOf(upComingMovieList.getId());

            ((UpComingMovieViewHolder) viewHolder).titleMovie.setText(upComingMovieList.getOriginalTitle());
            ((UpComingMovieViewHolder) viewHolder).dateMovie.setText(upComingMovieList.getReleaseDate());
            ((UpComingMovieViewHolder) viewHolder).itemView.setOnClickListener(v ->
                    DetailMovieActivity_.intent(context).extra("id",idFilm).start());

            Picasso.with(context)
                    .load(URL_BASE_IMAGE+upComingMovieList.getPosterPath())
                    .into(((UpComingMovieViewHolder) viewHolder).imageUpComingMovie);
        }
    }

    @Override
    public int getItemCount() {
        return upComingMovieLists.size();
    }
}
