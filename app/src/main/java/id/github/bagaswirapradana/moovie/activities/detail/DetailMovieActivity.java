package id.github.bagaswirapradana.moovie.activities.detail;

import android.os.Build;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.adapter.SimilarMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.TrailerAdapter;
import id.github.bagaswirapradana.moovie.behavior.StartSnapHelper;
import id.github.bagaswirapradana.moovie.model.DetailMovie;
import id.github.bagaswirapradana.moovie.model.SimilarMovie;
import id.github.bagaswirapradana.moovie.model.Videos;

@EActivity(R.layout.activity_detail_movie)
public class DetailMovieActivity extends AppCompatActivity implements IDetailMovieView{

    private DetailMoviePresenter detailMoviePresenter;

    private final static String TAG = "DetailMovieActivity";

    @ViewById(R.id.youtube_player_view)
    protected YouTubePlayerView youTubePlayerView;

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

    @AfterViews
    void initialize(){
        detailMoviePresenter = new DetailMoviePresenter(this);

        getLifecycle().addObserver(youTubePlayerView);

        String idMovie = Objects.requireNonNull(getIntent().getExtras()).getString("id");

        if (idMovie!=null){
            detailMoviePresenter.getDetailMovieData(idMovie);
            detailMoviePresenter.getSimilarMovieData(idMovie);
            detailMoviePresenter.getTrailerMovie(idMovie);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
        detailMoviePresenter.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        youTubePlayerView.release();
    }

    @Override
    public void setYoutubePlayerView(String key) {
        youTubePlayerView.initialize(youTubePlayer -> youTubePlayer.addListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady() {
               youTubePlayer.loadVideo(key,0);
            }
        }),true);
    }

    @Override
    public void onTrailerClicked(String key) {
        setYoutubePlayerView(key);
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        similarMovieList.setLayoutManager(linearLayoutManager);
        similarMovieList.setHasFixedSize(true);
        similarMovieList.setNestedScrollingEnabled(false);
        similarMovieList.setItemAnimator(new DefaultItemAnimator());

        if(similarMovie!=null) {
            Log.d(TAG,"similarMovies Succes!");
            SimilarMoviesAdapter similarMoviesAdapter = new SimilarMoviesAdapter(similarMovie.getResults(), this);
            similarMovieList.setAdapter(similarMoviesAdapter);
            SnapHelper startSnapHelper = new StartSnapHelper();
            startSnapHelper.attachToRecyclerView(similarMovieList);
            similarMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Similar Movies response null");
        }
    }

    @Override
    public void setTrailerMovieData(Videos videos) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        trailerRecyclerView.setLayoutManager(linearLayoutManager);
        trailerRecyclerView.setHasFixedSize(true);
        trailerRecyclerView.setNestedScrollingEnabled(false);
        trailerRecyclerView.setItemAnimator(new DefaultItemAnimator());

        if (videos!=null){
            Log.d(TAG,"trailer Succes!");
            TrailerAdapter trailerAdapter = new TrailerAdapter(videos.getResults(),getApplicationContext(),this);
            trailerRecyclerView.setAdapter(trailerAdapter);
            SnapHelper startSnapHelper = new StartSnapHelper();
            startSnapHelper.attachToRecyclerView(trailerRecyclerView);
            trailerAdapter.notifyDataSetChanged();

            setYoutubePlayerView(videos.getResults().get(0).getKey());
        }else {
            Log.d(TAG,"Trailer Movies Response Null");
        }
    }

    @Click(R.id.back)
    void onBackActivity(){
        finish();
    }
}