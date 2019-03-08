package id.github.bagaswirapradana.moovie.activities.detail;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

import id.github.bagaswirapradana.moovie.util.ExoPlayerManager;
import java.util.ArrayList;
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

    private final static String BASE_URL = "https://www.youtube.com";
    private final static String mYoutubeLink = BASE_URL + "/watch?v=";
    private final static String TAG = "DetailMovieActivity";

    private boolean mExoPlayerFullscreen = false;
    private Dialog mFullScreenDialog;

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
    @ViewById(R.id.exo_fullscreen)
    protected ImageButton mFullScreenButton;

    @Bean
    protected SimilarMoviesAdapter similarMoviesAdapter;
    @Bean
    protected TrailerAdapter trailerAdapter;
    @Bean
    protected DetailMoviePresenter detailMoviePresenter;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void extractUrl(String key) {
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
        detailMoviePresenter.setIDetailMovieView(this);
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
        if (detailMovie != null) {
            String year = detailMovie.getReleaseDate();
            String runTime = String.valueOf(detailMovie.getRuntime()) + " Minutes";
            Picasso.with(this)
                    .load("https://image.tmdb.org/t/p/w1280"+detailMovie.
                            getBackdropPath())
                    .into(imageView);
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
        if (videos != null && videos.getResults().size() >= 1){
            trailerAdapter.bindTrailerMovies(videos.getResults());
            trailerAdapter.setIDetailMovie(this);
            trailerAdapter.notifyDataSetChanged();
            extractUrl(videos.getResults().get(0).getKey());
        }else {
            Log.d(TAG,"Trailer Movies Response Null");
        }
    }

    @Override
    public void setTrailerPlayer(String url) {
        simpleExoPlayer.setPlayer(ExoPlayerManager.getSharedInstance(this).getPlayerView().getPlayer());
        ExoPlayerManager.getSharedInstance(this).playStream(url);
    }

    @Click(R.id.back)
    protected void onBackActivity(){
        finish();
    }

    private void initFullscreenDialog() {
        mFullScreenDialog = new Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen) {
            public void onBackPressed() {
                if (mExoPlayerFullscreen)
                    closeFullscreenDialog();
                super.onBackPressed();
            }
        };
    }

    private void openFullscreenDialog() {
        ((ViewGroup) simpleExoPlayer.getParent()).removeView(simpleExoPlayer);
        mFullScreenDialog.addContentView(simpleExoPlayer, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mFullScreenButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fullscreen_exit_24dp));
        mExoPlayerFullscreen = true;
        mFullScreenDialog.show();
    }

    private void closeFullscreenDialog() {
        ((ViewGroup) simpleExoPlayer.getParent()).removeView(simpleExoPlayer);
        ((FrameLayout) findViewById(R.id.main_media_frame)).addView(simpleExoPlayer);
        mExoPlayerFullscreen = false;
        mFullScreenDialog.dismiss();
        mFullScreenButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_fullscreen_24dp));
    }

    @Click(R.id.exo_fullscreen)
    protected void onClickFullScreen(){
        if (!mExoPlayerFullscreen)
            if (mFullScreenDialog == null){
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                initFullscreenDialog();
            }else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                openFullscreenDialog();
            }
        else
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            closeFullscreenDialog();
    }
}