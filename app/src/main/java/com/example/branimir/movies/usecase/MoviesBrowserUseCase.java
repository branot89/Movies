package com.example.branimir.movies.usecase;

import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.domain.model.PageableMovies;
import com.example.branimir.movies.usecase.dependency.remote.RemoteDao;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class MoviesBrowserUseCase {

    private final RemoteDao remoteDao;

    @Inject
    public MoviesBrowserUseCase(RemoteDao remoteDao) {
        this.remoteDao = remoteDao;
    }

    public Single<List<Movie>> getPopular() {
        return remoteDao.getPopular().map(PageableMovies::getMovies)
                .subscribeOn(Schedulers.io());
    }

    public Single<List<Movie>> getTopRated() {
        return remoteDao.getTopRated().map(PageableMovies::getMovies)
                .subscribeOn(Schedulers.io());
    }
}
