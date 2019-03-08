package id.github.bagaswirapradana.moovie.fragments.home;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.SnapHelper;
import android.util.Log;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.adapter.popular.PopularMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.rated.TopRatedMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.coming.UpComingMoviesAdapter;
import id.github.bagaswirapradana.moovie.behavior.SnappingRecyclerView;
import id.github.bagaswirapradana.moovie.behavior.StartSnapHelper;
import id.github.bagaswirapradana.moovie.model.PopularMovie;
import id.github.bagaswirapradana.moovie.model.TopRatedMovie;
import id.github.bagaswirapradana.moovie.model.UpComingMovie;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment implements IHomeView{

    private static final String TAG = "HomeFragment";
    private HomePresenter homePresenter;

    @ViewById(R.id.popularMovieList)
    protected SnappingRecyclerView recyclerViewPopular;
    @ViewById(R.id.topRatedList)
    protected ShimmerRecyclerView recyclerViewTopRated;
    @ViewById(R.id.upcomingList)
    protected ShimmerRecyclerView recyclerViewUpComing;

    @Bean
    protected PopularMoviesAdapter popularMoviesAdapter;
    @Bean
    protected TopRatedMoviesAdapter ratedMoviesAdapter;
    @Bean
    protected UpComingMoviesAdapter comingMoviesAdapter;

    @AfterViews
    protected void initialize(){
        homePresenter = new HomePresenter(this);
        homePresenter.getAllData();
        initViews();
        showShimmer();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayMoviesPopular(PopularMovie popularMovie) {
        if(popularMovie != null) {
            popularMoviesAdapter.bindPopularMovies(popularMovie.getResults());
            popularMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Popular Movies response null");
        }
    }

    @Override
    public void displayMoviesTopRated(TopRatedMovie topRatedMovie) {
        if(topRatedMovie != null) {
            ratedMoviesAdapter.bindTopRatedMovies(topRatedMovie.getResults());
            ratedMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Top Rated Movies response null");
        }
    }

    @Override
    public void displayMovieUpComing(UpComingMovie upComingMovie) {
        if(upComingMovie != null) {
            comingMoviesAdapter.bindUpComingMovies(upComingMovie.getResults());
            comingMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Up Coming Movies response null");
        }
    }

    @Override
    public void displayError(String s) {
        showToast(s);
    }

    @Override
    public void showShimmer() {
        recyclerViewPopular.showShimmerAdapter();
        recyclerViewTopRated.showShimmerAdapter();
        recyclerViewUpComing.showShimmerAdapter();
    }

    @Override
    public void hideShimmer() {
        recyclerViewPopular.hideShimmerAdapter();
        recyclerViewTopRated.hideShimmerAdapter();
        recyclerViewUpComing.hideShimmerAdapter();
    }

    @Override
    public void initViews() {
        //Popular Movies Recyclerview
        SnapHelper startSnapHelper = new StartSnapHelper();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager.scrollToPosition(0);

        recyclerViewPopular.setLayoutManager(linearLayoutManager);
        recyclerViewPopular.setHasFixedSize(true);
        recyclerViewPopular.setNestedScrollingEnabled(false);
        recyclerViewPopular.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopular.setAdapter(popularMoviesAdapter);
        recyclerViewPopular.enableViewScaling(true);
        //Top Rated Movies Recyclerview
        recyclerViewTopRated.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewTopRated.setHasFixedSize(true);
        recyclerViewTopRated.setNestedScrollingEnabled(false);
        recyclerViewTopRated.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTopRated.setAdapter(ratedMoviesAdapter);
        startSnapHelper.attachToRecyclerView(recyclerViewTopRated);
        //Upcoming Movies Recyclerview
        recyclerViewUpComing.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewUpComing.setHasFixedSize(true);
        recyclerViewUpComing.setNestedScrollingEnabled(false);
        recyclerViewUpComing.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUpComing.setAdapter(comingMoviesAdapter);
        startSnapHelper.attachToRecyclerView(recyclerViewUpComing);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onDestroy();
    }
}
