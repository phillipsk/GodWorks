package io.techministry.ui;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;

import io.techministry.BaseActivity;
import io.techministry.R;
import io.techministry.ui.bible.BibleFragment;
import io.techministry.ui.bible.chapter.ChapterFragment;

public class DashboardActivity extends BaseActivity {

    public BottomNavigationView bottomNavigationView;
//    public Toolbar toolbar;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);  // Deprecated
        Toolbar toolbar = findViewById(R.id.toolbar);

        fragmentManager = getSupportFragmentManager();

        setupBaseActionbar(toolbar, getString(R.string.app_name), false);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setBottomNavigation();

    }

    @Override
    protected void onPause() {
        super.onPause();
//        TODO: Reference by number instead of string R.id.value
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_bible);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_bible);
    }

    private void setBottomNavigation() {
        switchFragments(R.id.bottom_nav_bible);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switchFragments(item.getItemId());
            return true;
        });
    }

    private void switchFragments(int position) {
        Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(position));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        for (Fragment eachFragment : fragmentManager.getFragments()) {
            fragmentTransaction.hide(eachFragment);
        }

        if (fragment == null) {
            Fragment newFrag = getItem(position);
            fragmentTransaction.add(R.id.container, newFrag, String.valueOf(position));
            fragmentTransaction.commit();
        } else {
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    public Fragment getItem(int menu_id) {
        Fragment fragment = null;

//        fragment = new BibleFragment();


        if (menu_id == R.id.bottom_nav_listen) {
            fragment = new ChapterFragment();
        }else if(menu_id == R.id.bottom_nav_bible) {
            fragment = new BibleFragment();
        }else if (menu_id == R.id.bottom_nav_the_word){
            fragment = new BibleFragment();
        }
        return fragment;
    }

}
