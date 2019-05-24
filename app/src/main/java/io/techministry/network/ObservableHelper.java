package io.techministry.network;

import io.techministry.BuildConfig;
import io.techministry.GWApp;
import retrofit2.Retrofit;
import rx.Observable;

public class ObservableHelper {
    private IBibleApi iBibleApi;
    private static ObservableHelper instance;

    private ObservableHelper() {
        iBibleApi = getRetrofit().create(IBibleApi.class);
    }

    public static ObservableHelper getInstance(){
        if (instance == null){
            instance = new ObservableHelper();
        }

        return instance;
    }

    private static Retrofit getRetrofit() {
        return GWApp.provideRetrofit(BuildConfig.API_URL_BIBLE,BuildConfig.API_KEY_BIBLE);
    }

    public Observable<BibleBook> getBibBooks(String bibleID) {
        return iBibleApi.fetchBibBooks(bibleID);
                //.fetchTopHeadlines(countryCode, sources, category, query, page, BuildConfig.API_KEY);
    }

/*
    public Observable<ArticlesWrapper> getTopHeadlines(@Nullable String countryCode, @Nullable String sources, @Nullable String category, @Nullable String query, int page) {
        return iNewsAPI.fetchTopHeadlines(countryCode, sources, category, query, page, BuildConfig.API_KEY);
    }

    public Observable<ArticlesWrapper> getEverything(String query, String sortBy, String language) {
        return iNewsAPI.fetchEverything(query, sortBy, language, BuildConfig.API_KEY);
    }
*/

}
