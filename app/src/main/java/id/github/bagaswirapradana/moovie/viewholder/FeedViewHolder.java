package id.github.bagaswirapradana.moovie.viewholder;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.FeedList;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    private TextView judul,overview;
    private ImageView poster,backdrop;

    private final static String URL_BASE_IMAGE = "https://image.tmdb.org/t/p/w500/";
    private final static String URL_BACKDROP_IMAGE = "https://image.tmdb.org/t/p/w1280/";

    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        judul = itemView.findViewById(R.id.title);
        overview = itemView.findViewById(R.id.overview);
        poster = itemView.findViewById(R.id.poster);
        backdrop = itemView.findViewById(R.id.backdrop);
    }

    public void bindData(FeedList feedList, Context context){
        judul.setText(feedList.getOriginalTitle());
        overview.setText(feedList.getOverview());

        Picasso.with(context)
                .load(URL_BASE_IMAGE+feedList.getPosterPath())
                .into(poster);

        Picasso.with(context)
                .load(URL_BACKDROP_IMAGE+feedList.getBackdropPath())
                .into(backdrop);
    }
}
