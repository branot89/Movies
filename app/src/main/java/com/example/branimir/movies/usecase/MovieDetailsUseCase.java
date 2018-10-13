package com.example.branimir.movies.usecase;

import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.usecase.dependency.remote.RemoteDao;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsUseCase {

    private final RemoteDao remoteDao;

    @Inject
    public MovieDetailsUseCase(RemoteDao remoteDao) {
        this.remoteDao = remoteDao;
    }

    public Single<Movie> getMovieDetails(int movieId) {
        return remoteDao.getMovieDetails(movieId).subscribeOn(Schedulers.io());
    }
}
