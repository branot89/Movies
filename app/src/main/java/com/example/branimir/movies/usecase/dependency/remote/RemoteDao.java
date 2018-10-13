package com.example.branimir.movies.usecase.dependency.remote;

import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.domain.model.PageableMovies;
import io.reactivex.Single;

public interface RemoteDao {

    Single<PageableMovies> getPopular();

    Single<PageableMovies> getTopRated();

    Single<Movie> getMovieDetails(int movieId);
}
