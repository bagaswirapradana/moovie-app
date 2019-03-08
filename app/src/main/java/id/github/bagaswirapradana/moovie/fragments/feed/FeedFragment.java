package id.github.bagaswirapradana.moovie.fragments.feed;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.adapter.feed.FeedAdapter;
import id.github.bagaswirapradana.moovie.model.Feed;

@EBean
@EFragment(R.layout.fragment_feed)
public class FeedFragment extends Fragment implements IFeedView {

    private static final String TAG = "FeedFragment";

    private FeedPresenter feedPresenter;

    @Bean
    protected FeedAdapter feedAdapter;

    @ViewById(R.id.feedList)
    protected ShimmerRecyclerView recyclerViewFeed;

    @AfterViews
    protected void initialize(){
        feedPresenter = new FeedPresenter(this);
        feedPresenter.getFeedData();
        initViews();
        showShimmer();
    }

    @Override
    public void showShimmer() {
        recyclerViewFeed.showShimmerAdapter();
    }

    @Override
    public void hideShimmer() {
        recyclerViewFeed.hideShimmerAdapter();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

    @Override
    public void displayFeed(Feed feed) {
        if(feed != null) {
            feedAdapter.bindFeed(feed.getResults());
            feedAdapter.notifyDataSetChanged();
        }else {
            Log.d(TAG,"Feed response null");
        }
    }

    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewFeed.setLayoutManager(linearLayoutManager);
        recyclerViewFeed.setHasFixedSize(true);
        recyclerViewFeed.setNestedScrollingEnabled(false);
        recyclerViewFeed.setItemAnimator(new DefaultItemAnimator());
        recyclerViewFeed.setAdapter(feedAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        feedPresenter.onDestroy();
    }
}
