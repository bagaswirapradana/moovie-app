package id.github.bagaswirapradana.moovie.fragments.home;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.SnapHelper;
import android.util.Log;
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.adapter.PopularMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.TopRatedMoviesAdapter;
import id.github.bagaswirapradana.moovie.adapter.UpComingMoviesAdapter;
import id.github.bagaswirapradana.moovie.behavior.SnappingRecyclerView;
import id.github.bagaswirapradana.moovie.behavior.StartSnapHelper;
import id.github.bagaswirapradana.moovie.model.PopularMovie;
import id.github.bagaswirapradana.moovie.model.TopRatedMovie;
import id.github.bagaswirapradana.moovie.model.UpComingMovie;

@EFragment(R.layout.fragment_home)
public class HomeFragment extends Fragment implements IHomeView{

    private String TAG = "HomeFragment";

    private HomePresenter homePresenter;

    @ViewById(R.id.popularMovieList)
    protected SnappingRecyclerView recyclerViewPopular;

    @ViewById(R.id.topRatedList)
    protected ShimmerRecyclerView recyclerViewTopRated;

    @ViewById(R.id.upcomingList)
    protected ShimmerRecyclerView recyclerViewUpComing;

    @AfterViews
    void initialize(){
        homePresenter = new HomePresenter(this);
        homePresenter.getAllData();
        showShimmer();
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayMoviesPopular(PopularMovie popularMovie) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        linearLayoutManager.scrollToPosition(0);
        recyclerViewPopular.setLayoutManager(linearLayoutManager);
        recyclerViewPopular.setHasFixedSize(true);
        recyclerViewPopular.setNestedScrollingEnabled(false);
        recyclerViewPopular.setItemAnimator(new DefaultItemAnimator());

        if(popularMovie!=null) {
            Log.d(TAG,popularMovie.getResults().get(1).getTitle());
            PopularMoviesAdapter popularMoviesAdapter = new PopularMoviesAdapter(popularMovie.getResults(), getContext());
            recyclerViewPopular.setAdapter(popularMoviesAdapter);
            recyclerViewPopular.enableViewScaling(true);
            popularMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Popular Movies response null");
        }
    }

    @Override
    public void displayMoviesTopRated(TopRatedMovie topRatedMovie) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewTopRated.setLayoutManager(linearLayoutManager);
        recyclerViewTopRated.setHasFixedSize(true);
        recyclerViewTopRated.setNestedScrollingEnabled(false);
        recyclerViewTopRated.setItemAnimator(new DefaultItemAnimator());

        if(topRatedMovie!=null) {
            Log.d(TAG,topRatedMovie.getResults().get(1).getTitle());
            TopRatedMoviesAdapter topRatedMoviesAdapter = new TopRatedMoviesAdapter(topRatedMovie.getResults(), getContext());
            recyclerViewTopRated.setAdapter(topRatedMoviesAdapter);
            SnapHelper startSnapHelper = new StartSnapHelper();
            startSnapHelper.attachToRecyclerView(recyclerViewTopRated);
            topRatedMoviesAdapter.notifyDataSetChanged();
        }else{
            Log.d(TAG,"Top Rated Movies response null");
        }
    }

    @Override
    public void displayMovieUpComing(UpComingMovie upComingMovie) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewUpComing.setLayoutManager(linearLayoutManager);
        recyclerViewUpComing.setHasFixedSize(true);
        recyclerViewUpComing.setNestedScrollingEnabled(false);
        recyclerViewUpComing.setItemAnimator(new DefaultItemAnimator());

        if(upComingMovie!=null) {
            Log.d(TAG,upComingMovie.getResults().get(1).getTitle());
            UpComingMoviesAdapter upComingMoviesAdapter = new UpComingMoviesAdapter(upComingMovie.getResults(), getContext());
            recyclerViewUpComing.setAdapter(upComingMoviesAdapter);
            SnapHelper startSnapHelper = new StartSnapHelper();
            startSnapHelper.attachToRecyclerView(recyclerViewUpComing);
            upComingMoviesAdapter.notifyDataSetChanged();
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
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.onDestroy();
    }
}
