package com.example.branimir.movies.repository.remote.httpclient.remoteDao;

import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.domain.model.PageableMovies;
import com.example.branimir.movies.repository.remote.httpclient.HttpClient;
import com.example.branimir.movies.repository.remote.httpclient.dto.PageableResult;
import com.example.branimir.movies.usecase.dependency.remote.RemoteDao;
import javax.inject.Inject;
import io.reactivex.Single;

public class RemoteDaoImpl implements RemoteDao {

    private final HttpClient httpClient;

    @Inject
    public RemoteDaoImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public Single<PageableMovies> getPopular() {
        return httpClient.getPopular().map(PageableResult::toDomainModel);
    }

    @Override
    public Single<PageableMovies> getTopRated() {
        return httpClient.getTopRated().map(PageableResult::toDomainModel);
    }

    @Override
    public Single<Movie> getMovieDetails(int movieId) {
        return httpClient.getMovieDetails(movieId).map(movie -> movie.toDomainModel());
    }
}
