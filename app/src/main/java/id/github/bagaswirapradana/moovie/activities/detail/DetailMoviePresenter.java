package id.github.bagaswirapradana.moovie.activities.detail;

import android.util.Log;

import id.github.bagaswirapradana.moovie.model.DetailMovie;
import id.github.bagaswirapradana.moovie.model.SimilarMovie;
import id.github.bagaswirapradana.moovie.model.Videos;
import id.github.bagaswirapradana.moovie.network.GetDataService;
import id.github.bagaswirapradana.moovie.network.RetrofitClientInstance;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.androidannotations.annotations.EBean;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

@EBean
public class DetailMoviePresenter implements IDetailPresenter{

    private IDetailMovieView iDetailMovieView;

    private static final String TAG = "DetailMoviePresenter";
    private static final String API_KEY = "ef037f53cdf93d69c631efff89016d3b";
    private static final String LANGUAGE = "id";
    private static final String APPEND_TO_RESPONSE = "videos";

    @Override
    public void getDetailMovieData(String id) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableDetailMovieData(id).subscribeWith(getObserverDetailMovieData()));
    }

    @Override
    public void getSimilarMovieData(String id) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableSimilarMovieData(id).subscribeWith(getObserverSimilarMovieData()));
    }

    private DisposableObserver<SimilarMovie> getObserverSimilarMovieData() {
        return new DisposableObserver<SimilarMovie>() {
            @Override
            public void onNext(SimilarMovie similarMovie) {
                Log.d(TAG,"OnNext"+similarMovie.getTotalResults());
                if (iDetailMovieView!=null){
                    iDetailMovieView.setSimilarMovieData(similarMovie);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Similar Completed");
            }
        };
    }

    private Observable<SimilarMovie> getObservableSimilarMovieData(String id) {
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getSimilarMovie(id,API_KEY,LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    @Override
    public void onDestroy() {
        iDetailMovieView = null;
    }

    @Override
    public void getTrailerMovie(String id) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableTrailerMovie(id).subscribeWith(getObserverTrailerMovie()));
    }

    @Override
    public void setIDetailMovieView(IDetailMovieView iDetailMovieView) {
        this.iDetailMovieView = iDetailMovieView;
    }

    private DisposableObserver<Videos> getObserverTrailerMovie() {
        return new DisposableObserver<Videos>() {
            @Override
            public void onNext(Videos videos) {
                Log.d(TAG,"OnNext"+videos.getResults());
                if (iDetailMovieView != null){
                    iDetailMovieView.setTrailerMovieData(videos);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Trailer Movie Completed");
            }
        };
    }

    private Observable<Videos> getObservableTrailerMovie(String id) {
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getVideosTrailer(id,API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    private DisposableObserver<DetailMovie> getObserverDetailMovieData() {
        return new DisposableObserver<DetailMovie>() {
            @Override
            public void onNext(DetailMovie detailMovie) {
                Log.d(TAG,"OnNext"+detailMovie.getId());
                if (iDetailMovieView != null){
                    iDetailMovieView.setDetailData(detailMovie);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Detail Movie Completed");
            }
        };
    }

    private Observable<DetailMovie> getObservableDetailMovieData(String id) {
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getDetailMovie(id,API_KEY,LANGUAGE,APPEND_TO_RESPONSE)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }
}
