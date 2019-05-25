package io.techministry.network;

import java.io.IOException;

import io.techministry.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Response;

public class BibleApiInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder()
                .addHeader("api-key", BuildConfig.API_KEY_BIBLE)
                .build());
    }
}
