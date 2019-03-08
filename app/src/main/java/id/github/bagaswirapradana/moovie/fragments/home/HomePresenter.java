package id.github.bagaswirapradana.moovie.fragments.home;

import androidx.annotation.NonNull;
import android.util.Log;

import id.github.bagaswirapradana.moovie.model.PopularMovie;
import id.github.bagaswirapradana.moovie.model.TopRatedMovie;
import id.github.bagaswirapradana.moovie.model.UpComingMovie;
import id.github.bagaswirapradana.moovie.network.GetDataService;
import id.github.bagaswirapradana.moovie.network.RetrofitClientInstance;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import org.androidannotations.annotations.EBean;

import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;

@EBean
public class HomePresenter implements IHomePresenter{

    private IHomeView iHomeView;

    private static final String TAG = "HomePresenter";
    private static final String API_KEY = "ef037f53cdf93d69c631efff89016d3b";
    private static final String LANGUAGE = "id";

    @Override
    public void setIHomeView(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    @Override
    public void getAllData() {
        getMoviesPopular();
        getMoviesTopRated();
        getMoviesUpComing();
    }

    @Override
    public void getMoviesPopular() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservablePopularMovie().subscribeWith(getObserverPopularMovie()));
    }

    @Override
    public void getMoviesTopRated() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableTopRatedMovie().subscribeWith(getObserverTopRatedMovie()));
    }

    @Override
    public void getMoviesUpComing() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(getObservableUpComingMovie().subscribeWith(getObserverUpComingMovie()));
    }

    private Observable<UpComingMovie> getObservableUpComingMovie() {
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getDataUpComingMovie(API_KEY,LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    private Observable<PopularMovie> getObservablePopularMovie(){
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getDataPopularMovie(API_KEY,LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    private Observable<TopRatedMovie> getObservableTopRatedMovie(){
        return RetrofitClientInstance.getRetrofitClientInstance()
                .create(GetDataService.class)
                .getDataTopRatedMovie(API_KEY,LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(mainThread());
    }

    private DisposableObserver<PopularMovie> getObserverPopularMovie(){
        return new DisposableObserver<PopularMovie>() {

            @Override
            public void onNext(PopularMovie value) {
                Log.d(TAG,"OnNext"+value.getTotalResults());
                if (iHomeView!=null) {
                    iHomeView.displayMoviesPopular(value);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                if (iHomeView!=null) {
                    iHomeView.displayError("Error fetching Popular Movie Data");
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                if (iHomeView!=null) {
                    iHomeView.hideShimmer();
                }
            }
        };
    }

    private DisposableObserver<TopRatedMovie> getObserverTopRatedMovie(){
        return new DisposableObserver<TopRatedMovie>() {

            @Override
            public void onNext(TopRatedMovie value) {
                Log.d(TAG,"OnNext"+value.getTotalResults());
                if (iHomeView!=null) {
                    iHomeView.displayMoviesTopRated(value);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                if (iHomeView!=null) {
                    iHomeView.displayError("Error fetching Top Rated Movie Data");
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                if (iHomeView!=null) {
                    iHomeView.hideShimmer();
                }
            }
        };
    }

    private DisposableObserver<UpComingMovie> getObserverUpComingMovie() {
        return new DisposableObserver<UpComingMovie>() {

            @Override
            public void onNext(UpComingMovie value) {
                Log.d(TAG,"OnNext"+value.getTotalResults());
                if (iHomeView!=null) {
                    iHomeView.displayMovieUpComing(value);
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                if (iHomeView!=null) {
                    iHomeView.displayError("Error fetching Up Coming Movie Data");
                }
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                if (iHomeView!=null) {
                    iHomeView.hideShimmer();
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        iHomeView = null;
    }
}
