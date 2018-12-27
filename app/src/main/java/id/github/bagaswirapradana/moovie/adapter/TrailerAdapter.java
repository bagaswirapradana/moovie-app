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
import id.github.bagaswirapradana.moovie.activities.detail.IDetailMovieView;
import id.github.bagaswirapradana.moovie.model.VideosList;
import id.github.bagaswirapradana.moovie.viewholder.TrailerViewHolder;

public class TrailerAdapter extends RecyclerView.Adapter {

    private List<VideosList> videosList;

    private Context context;

    private IDetailMovieView iDetailMovieView;

    public TrailerAdapter(List<VideosList> videosList, Context context, IDetailMovieView iDetailMovieView) {
        this.videosList = videosList;
        this.context = context;
        this.iDetailMovieView = iDetailMovieView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer_movie, parent,false);
        viewHolder = new TrailerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TrailerViewHolder){

            final VideosList videos = videosList.get(position);

            Picasso.with(context)
                    .load("https://img.youtube.com/vi/"+videos.getKey()+"/hqdefault.jpg")
                    .into(((TrailerViewHolder) holder).imageView);

            ((TrailerViewHolder) holder).itemView.setOnClickListener(v -> iDetailMovieView.onTrailerClicked(videosList.get(position).getKey()));
        }
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }
}
