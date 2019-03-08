package id.github.bagaswirapradana.moovie.adapter.trailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.activities.detail.IDetailMovieView;
import id.github.bagaswirapradana.moovie.model.VideosList;
import id.github.bagaswirapradana.moovie.viewholder.TrailerViewHolder;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> implements TrailerFinder{

    @RootContext
    protected Context context;

    private List<VideosList> videosList;
    private IDetailMovieView iDetailMovieView;

    @NonNull
    @Override
    public TrailerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TrailerViewHolder(LayoutInflater
                .from(context)
                .inflate(R.layout.item_trailer_movie, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, int position) {
        if (!videosList.isEmpty()){
            holder.bindData(videosList.get(position),context);

            holder.itemView.setOnClickListener(v ->
                    iDetailMovieView
                            .onTrailerClicked(videosList
                                    .get(position)
                                    .getKey()));
        }
    }

    @Override
    public int getItemCount() {
        if (videosList == null || videosList.size() == 0){
            return 0;
        }else {
            return videosList.size();
        }
    }

    @Override
    public void bindTrailerMovies(List<VideosList> videosLists) {
        this.videosList = videosLists;
    }

    @Override
    public void setIDetailMovie(IDetailMovieView iDetailMovie) {
        this.iDetailMovieView = iDetailMovie;
    }
}
