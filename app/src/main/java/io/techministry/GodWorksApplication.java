package io.techministry;

import android.app.Application;
import android.content.Context;

import io.techministry.network.BibleApiManager;

public class GodWorksApplication extends Application {

    Context context;
    private BibleApiManager bibleApiManager;
    //private static FellowshipApplication sharedInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        bibleApiManager = BibleApiManager.getInstance();
    }

    public BibleApiManager getBibleApiManager() {
        return bibleApiManager;
    }
}
