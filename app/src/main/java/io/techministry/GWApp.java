package io.techministry;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.techministry.db.local.BibleDatabase;
import io.techministry.network.BibleApiManager;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GWApp extends Application {

    Context context;
    private BibleApiManager bibleApiManager;
    //private static FellowshipApplication sharedInstance;
    private BibleDatabase bibleDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        bibleApiManager = BibleApiManager.getInstance();
//        bibleDatabase = bibleDatabase.
    }

    public BibleApiManager getBibleApiManager() {
        return bibleApiManager;
    }

    public static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
    }

    public static Retrofit provideRetrofit(String url, String key) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(10, TimeUnit.SECONDS);
        httpClient.readTimeout(10, TimeUnit.SECONDS);
        httpClient.addInterceptor(new StethoInterceptor());
//        httpClient.a
        httpClient.interceptors().add(logging);

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL_BIBLE)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
                .client(httpClient.build())
                .build();
    }
}
