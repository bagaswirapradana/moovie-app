package id.github.bagaswirapradana.moovie.activities.detail;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.TextView;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import com.google.android.exoplayer2.ui.PlayerView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import id.github.bagaswirapradana.moovie.util.ExoPlayerManager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.ViewById;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.adapter.similar.SimilarMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.trailer.TrailerAdapter;
import id.github.bagaswirapradana.moovie.behavior.StartSnapHelper;
import id.github.bagaswirapradana.moovie.model.DetailMovie;
import id.github.bagaswirapradana.moovie.model.SimilarMovie;
import id.github.bagaswirapradana.moovie.model.Videos;

@Fullscreen
@EActivity(R.layout.activity_detail_movie)
public class DetailMovieActivity extends AppCompatActivity implements IDetailMovieView{

    private String BASE_URL = "https://www.youtube.com";
    private String mYoutubeLink = BASE_URL + "/watch?v=";

    private DetailMoviePresenter detailMoviePresenter;

    private final static String TAG = "DetailMovieActivity";

    @Extra("id")
    protected String idMovie;

    @ViewById(R.id.titles)
    protected TextView titleMovie;
    @ViewById(R.id.status)
    protected TextView statusMovie;
    @ViewById(R.id.year)
    protected TextView releaseDate;
    @ViewById(R.id.overview)
    protected TextView overview;
    @ViewById(R.id.image)
    protected ImageView imageView;
    @ViewById(R.id.playtime)
    protected TextView playTime;
    @ViewById(R.id.similarMovieList)
    protected RecyclerView similarMovieList;
    @ViewById(R.id.trailers)
    protected RecyclerView trailerRecyclerView;
    @ViewById(R.id.genre)
    protected TextView genreMovie;
    @ViewById(R.id.exoplayer)
    protected PlayerView simpleExoPlayer;

    @Bean
    protected SimilarMoviesAdapter similarMoviesAdapter;
    @Bean
    protected TrailerAdapter trailerAdapter;

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void extractUrl(String key) {
        @SuppressLint("StaticFieldLeak") YouTubeExtractor mYouTubeExtractor =  new YouTubeExtractor(this) {
            @Override
            protected void onExtractionComplete(SparseArray<YtFile> sparseArray,
                    VideoMeta videoMeta) {
                    if (sparseArray != null){
                        setTrailerPlayer(sparseArray.get(18).getUrl());
                    }
            }
        };
        mYouTubeExtractor.extract(mYoutubeLink+key,true,true);
    }

    @AfterViews
    protected void initialize(){
        detailMoviePresenter = new DetailMoviePresenter(this);

        initViews();

        if (idMovie != null){
            detailMoviePresenter.getDetailMovieData(idMovie);
            detailMoviePresenter.getSimilarMovieData(idMovie);
            detailMoviePresenter.getTrailerMovie(idMovie);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ExoPlayerManager.getSharedInstance(this).destroyPlayer();
        detailMoviePresenter.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();
        ExoPlayerManager.getSharedInstance(this).stopPlayer(true);
    }

    @Override
    public void initViews() {
        //Similar Movie Recyclerview
        SnapHelper startSnapHelper = new StartSnapHelper();
        similarMovieList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        similarMovieList.setHasFixedSize(true);
        similarMovieList.setNestedScrollingEnabled(false);
        similarMovieList.setItemAnimator(new DefaultItemAnimator());
        similarMovieList.setAdapter(similarMoviesAdapter);
        startSnapHelper.attachToRecyclerView(similarMovieList);
        //Trailer Movie Recyclerview
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailerRecyclerView.setHasFixedSize(true);
        trailerRecyclerView.setNestedScrollingEnabled(false);
        trailerRecyclerView.setItemAnimator(new DefaultItemAnimator());
        trailerRecyclerView.setAdapter(trailerAdapter);
        startSnapHelper.attachToRecyclerView(trailerRecyclerView);
    }

    @Override
    public void onTrailerClicked(String key) {
        extractUrl(key);
    }

    @Override
    public void setDetailData(DetailMovie detailMovie) {
        if (detailMovie!=null) {
            String year = detailMovie.getReleaseDate();
            String runTime = String.valueOf(detailMovie.getRuntime()) + " Minutes";
            Picasso.with(this).load("https://image.tmdb.org/t/p/w1280"+detailMovie.getBackdropPath()).into(imageView);
            titleMovie.setText(detailMovie.getOriginalTitle());
            statusMovie.setText(detailMovie.getStatus());
            overview.setText(detailMovie.getOverview());
            playTime.setText(runTime);
            if (year != null) {
                releaseDate.setText(detailMovie.getReleaseDate().subSequence(0, 4));
            } else {
                releaseDate.setText(detailMovie.getReleaseDate());
            }

            final String[] genres = {""};
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                detailMovie.getGenres().forEach(genre1 -> genres[0] += " | " + genre1.getName());
            }
            genreMovie.setText(genres[0]);
        }
    }

    @Override
    public void setSimilarMovieData(SimilarMovie similarMovie) {
        if(similarMovie != null) {
            similarMoviesAdapter.bindSimilarMovies(similarMovie.getResults());
            similarMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Similar Movies response null");
        }
    }

    @Override
    public void setTrailerMovieData(Videos videos) {
        if (videos != null){
            trailerAdapter.bindTrailerMovies(videos.getResults());
            trailerAdapter.setIDetailMovie(this);
            trailerAdapter.notifyDataSetChanged();
            extractUrl(videos.getResults().get(0).getKey());
        }else {
            Log.d(TAG,"Trailer Movies Response Null");
        }
    }

    @Override
    public void setTrailerPlayer(String key) {
        simpleExoPlayer.setPlayer(ExoPlayerManager.getSharedInstance(this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(this).playStream(key);
    }

    @Click(R.id.back)
    protected void onBackActivity(){
        finish();
    }
}