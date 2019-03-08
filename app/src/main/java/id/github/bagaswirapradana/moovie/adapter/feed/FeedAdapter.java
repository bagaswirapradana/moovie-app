package id.github.bagaswirapradana.moovie.adapter.feed;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.model.FeedList;
import id.github.bagaswirapradana.moovie.viewholder.FeedViewHolder;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> implements FeedFinder{

    @RootContext
    protected Context context;

    private List<FeedList> feedLists;

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       return new FeedViewHolder(LayoutInflater
               .from(context)
               .inflate(R.layout.item_feed,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        if (!feedLists.isEmpty()){
            holder.bindData(feedLists.get(position),context);
        }
    }

    @Override
    public int getItemCount() {
        if (feedLists == null || feedLists.size() == 0){
            return 0;
        }else {
            return feedLists.size();
        }
    }

    @Override
    public void bindFeed(List<FeedList> feedList) {
        this.feedLists = feedList;
    }
}
