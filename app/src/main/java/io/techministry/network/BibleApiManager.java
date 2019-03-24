package io.techministry.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

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
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(apiClient)
                .baseUrl(URL_RUTH)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(BibleApi.class);
    }

    public Gson createGson() {
        return new GsonBuilder().create();
    }

}
