package id.github.bagaswirapradana.moovie.fragments.feed;

import android.util.Log;

import id.github.bagaswirapradana.moovie.model.Feed;
import id.github.bagaswirapradana.moovie.network.GetDataService;
import id.github.bagaswirapradana.moovie.network.RetrofitClientInstance;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

public class FeedPresenter implements IFeedPresenter{

    private static final String API_KEY = "ef037f53cdf93d69c631efff89016d3b";

    private static final String TAG = "FeedPresenter";

    private IFeedView iFeedView;

    FeedPresenter(IFeedView iFeedView) {
        this.iFeedView = iFeedView;
    }

    @Override
    public void getFeedData() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableFeedData().subscribeWith(getObserverFeedData()));
    }

    @Override
    public void onDestroy() {
        iFeedView = null;
    }

    private DisposableObserver<Feed> getObserverFeedData() {
        return new DisposableObserver<Feed>() {

            @Override
            public void onNext(Feed feed) {
                Log.d(TAG,"OnNext"+feed.getTotalResults());
                if (iFeedView!=null) {
                    iFeedView.hideShimmer();
                    iFeedView.displayFeed(feed);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                if (iFeedView!=null) {
                    iFeedView.displayError("Error fetching Feed");
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                if (iFeedView!=null) {
                    iFeedView.hideShimmer();
                }
            }
        };
    }

    private Observable<Feed> getObservableFeedData(){
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getDataFeed(API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }
}
