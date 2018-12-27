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
import id.github.bagaswirapradana.moovie.model.FeedList;
import id.github.bagaswirapradana.moovie.viewholder.FeedViewHolder;

public class FeedAdapter extends RecyclerView.Adapter {

    private List<FeedList> feedLists;
    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private final static String URL_BACKDROP_IMAGE = "https://image.tmdb.org/t/p/w1280/";
    private Context context;

    public FeedAdapter(List<FeedList> feedLists, Context context) {
        this.feedLists = feedLists;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_feed,viewGroup,false);
        viewHolder = new FeedViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof FeedViewHolder){
            FeedList feedList = feedLists.get(i);

            String title = feedList.getTitle();
            String idFilm = String.valueOf(feedList.getId());

            if (title!=null){
                ((FeedViewHolder) viewHolder).judul.setText(feedList.getTitle());
            }else{
                ((FeedViewHolder) viewHolder).judul.setText(feedList.getOriginalName());
            }

            ((FeedViewHolder) viewHolder).overview.setText(feedList.getOverview());

            viewHolder.itemView.setOnClickListener(v -> DetailMovieActivity_.intent(context).extra("id",idFilm).start());

            Picasso.with(context)
                    .load(URL_BASE_IMAGE+feedList.getPosterPath())
                    .into(((FeedViewHolder) viewHolder).poster);

            Picasso.with(context)
                    .load(URL_BACKDROP_IMAGE+feedList.getBackdropPath())
                    .into(((FeedViewHolder) viewHolder).backdrop);
        }
    }

    @Override
    public int getItemCount() {
        return feedLists.size();
    }
}
