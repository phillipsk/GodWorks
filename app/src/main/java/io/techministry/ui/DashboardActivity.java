package io.techministry.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

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
//        GodWorks heading title
        Toolbar toolbar = findViewById(R.id.toolbar);
        setupBaseActionbar(toolbar, getString(R.string.app_name), false);

        fragmentManager = getSupportFragmentManager();


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        setBottomNavigation();
        bottomNavigationView.setSelectedItemId(R.id.bottom_nav_bible);


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
//        This is where the initial fragment is called/created; parameterized with int position
//        switchFragments(R.id.bottom_nav_bible);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                DashboardActivity.this.switchFragments(item.getItemId());
                return true;
            }
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
