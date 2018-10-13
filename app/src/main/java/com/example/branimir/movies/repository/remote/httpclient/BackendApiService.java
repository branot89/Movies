package com.example.branimir.movies.repository.remote.httpclient;

import com.example.branimir.movies.repository.remote.httpclient.dto.Movie;
import com.example.branimir.movies.repository.remote.httpclient.dto.PageableResult;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BackendApiService {

    @GET("movie/popular")
    Single<PageableResult> getPopular();

    @GET("movie/top_rated")
    Single<PageableResult> getTopRated();

    @GET("movie/{id}")
    Single<Movie> getMovieDetails(@Path("id") int id);
}
