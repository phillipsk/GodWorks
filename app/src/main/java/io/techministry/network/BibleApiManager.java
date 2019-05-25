package io.techministry.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.techministry.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BibleApiManager {

    private static BibleApiManager instance;
    private final BibleApi bibleApi;
    private final Gson gson;

    private BibleApiManager() {
        bibleApi = createBibleApi();
        gson = createGson();
    }

    public static BibleApiManager getInstance() {
        if (instance == null) {
            instance = new BibleApiManager();
        }
        return instance;
    }

    public BibleApi getBibleApi() {
        return bibleApi;
    }

    public Gson getGson() {
        return gson;
    }

    private static String URL_RUTH = "https://api.scripture.api.bible";

    public BibleApi createBibleApi() {
        OkHttpClient apiClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .addNetworkInterceptor(new BibleApiInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(apiClient)
                .baseUrl(BuildConfig.API_URL_BIBLE)
                .addConverterFactory(GsonConverterFactory.create(provideGson()))
//                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(BibleApi.class);
    }

    public Gson createGson() {
        return new GsonBuilder().create();
    }
    public static Gson provideGson() {
        return new GsonBuilder()
                .setLenient()
                .serializeNulls()
                .create();
    }

}
