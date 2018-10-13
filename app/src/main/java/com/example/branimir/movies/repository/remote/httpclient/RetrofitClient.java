package com.example.branimir.movies.repository.remote.httpclient;

import com.example.branimir.movies.BuildConfig;
import com.example.branimir.movies.repository.remote.httpclient.dto.Movie;
import com.example.branimir.movies.repository.remote.httpclient.dto.PageableResult;
import javax.inject.Inject;
import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements HttpClient {

    private final BackendApiService service;

    @Inject
    public RetrofitClient() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("api_key", BuildConfig.API_KEY)
                            .build();

                    Request.Builder requestBuilder = original.newBuilder().url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                });


        final OkHttpClient client = okHttpBuilder.build();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(BackendApiService.class);
    }


    @Override
    public Single<PageableResult> getPopular() {
        return service.getPopular();
    }

    @Override
    public Single<PageableResult> getTopRated() {
        return service.getTopRated();
    }

    @Override
    public Single<Movie> getMovieDetails(int movieId) {
        return service.getMovieDetails(movieId);
    }
}
