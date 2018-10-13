package com.example.branimir.movies.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.branimir.movies.MoviesApplication;
import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.usecase.MoviesBrowserUseCase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;

public class TopRatedMoviesFragment extends MoviesFragment {

    @Inject
    MoviesBrowserUseCase moviesBrowserUseCase;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public static TopRatedMoviesFragment newInstance() {
        return new TopRatedMoviesFragment();
    }


    @Override
    public Single<List<Movie>> getMovies() {
        return moviesBrowserUseCase.getTopRated();
    }

    @Override
    protected void injectDependencies() {
        ((MoviesApplication) getActivity().getApplication()).getDependencyInjectionComponent().inject(this);
    }
}
