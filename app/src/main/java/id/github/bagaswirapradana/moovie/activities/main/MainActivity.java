package id.github.bagaswirapradana.moovie.activities.main;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

import id.github.bagaswirapradana.moovie.R;
import id.github.bagaswirapradana.moovie.behavior.BottomNavigationBehavior;
import id.github.bagaswirapradana.moovie.fragments.feed.FeedFragment_;
import id.github.bagaswirapradana.moovie.fragments.home.HomeFragment_;
import id.github.bagaswirapradana.moovie.fragments.more.MoreFragment_;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements IMainView {

    @ViewById(R.id.toolbar)
    protected Toolbar toolbar;
    @ViewById(R.id.navigation)
    protected BottomNavigationView bottomNavigationView;
    @ViewById(R.id.tv_judul)
    protected TextView tvJudul;

    private Fragment fragment;

    @AfterViews
    protected void initialize(){
        initToolbar();
        setNavigationBehavior();
        loadFragment(new HomeFragment_());

        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.navigation_home:
                    setTitle(getString(R.string.title_home));
                    fragment = new HomeFragment_();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_feed:
                    setTitle(getString(R.string.title_feed));
                    fragment = new FeedFragment_();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_more:
                    setTitle(getString(R.string.title_more));
                    fragment = new MoreFragment_();
                    loadFragment(fragment);
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void initToolbar() {
        setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);
        tvJudul.setText(toolbar.getTitle());
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    }

    @Override
    public void setNavigationBehavior() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
    }
}
