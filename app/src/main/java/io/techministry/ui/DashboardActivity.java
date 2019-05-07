package io.techministry.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import io.techministry.BaseActivity;
import io.techministry.R;
import io.techministry.network.BibleBook;
import io.techministry.ui.bible.BibleFragment;
import io.techministry.ui.bible.chapter.ChapterFragment;

public class DashboardActivity extends BaseActivity implements BibleFragment.BibleFragmentDelegate {

    public BottomNavigationView bottomNavigationView;
//    public Toolbar toolbar;

    private FragmentManager fragmentManager;
    private BibleFragment mBibleFragment;

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

    @Override
    public void onBackPressed() {
        // TODO: show the appropriate fragment instead of closing the activity (if needed)
        super.onBackPressed();
    }

    private void setBottomNavigation() {
//        This is where the initial fragment is called/created; parameterized with int position
//        switchFragments(R.id.bottom_nav_bible);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchFragments(item.getItemId());
                return true;
            }
        });
    }

    private void switchFragments(int position) {
        switchFragments(position, null);
    }

    private void switchFragments(int position, Serializable serializable) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment newFrag = getItem(position, serializable);
        fragmentTransaction.replace(R.id.container, newFrag, String.valueOf(position));

        fragmentTransaction.commit();
    }

    // TODO : consider the Parcelable interface instead of Serializable
    public Fragment getItem(int menu_id, Serializable serializable) {
        switch (menu_id) {
            case R.id.bottom_nav_listen:
                BibleBook book = (BibleBook) serializable;
                return ChapterFragment.getInstance(book);
            case R.id.bottom_nav_bible:
            case R.id.bottom_nav_the_word:
                if (mBibleFragment == null)
                    mBibleFragment = new BibleFragment();
                return mBibleFragment;
        }

        return null;
    }

    @Override
    public void onBookSelected(BibleBook book) {
        switchFragments(R.id.bottom_nav_listen, book);
    }
}
