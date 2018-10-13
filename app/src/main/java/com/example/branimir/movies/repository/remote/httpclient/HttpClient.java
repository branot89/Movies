package com.example.branimir.movies.repository.remote.httpclient;

import com.example.branimir.movies.repository.remote.httpclient.dto.Movie;
import com.example.branimir.movies.repository.remote.httpclient.dto.PageableResult;
import io.reactivex.Single;

public interface HttpClient {

    Single<PageableResult> getPopular();

    Single<PageableResult> getTopRated();

    Single<Movie> getMovieDetails(int movieId);
}
