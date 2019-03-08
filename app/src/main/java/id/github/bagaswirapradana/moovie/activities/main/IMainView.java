package id.github.bagaswirapradana.moovie.activities.main;

import androidx.fragment.app.Fragment;

public interface IMainView {
    void loadFragment(Fragment fragment);
    void initToolbar();
    void setNavigationBehavior();
}
