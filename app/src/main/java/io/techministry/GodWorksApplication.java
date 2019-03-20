package io.techministry;

import android.app.Application;
import android.content.Context;

public class GodWorksApplication extends Application {

    Context context;
    //private static FellowshipApplication sharedInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
