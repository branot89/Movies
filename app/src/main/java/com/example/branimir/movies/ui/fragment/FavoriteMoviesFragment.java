package com.example.branimir.movies.ui.fragment;

import com.example.branimir.movies.MoviesApplication;
import com.example.branimir.movies.domain.model.Movie;
import com.example.branimir.movies.usecase.MoviesBrowserUseCase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Single;

public class FavoriteMoviesFragment extends MoviesFragment {

    @Inject
    MoviesBrowserUseCase moviesBrowserUseCase;

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((MoviesApplication) getActivity().getApplication()).getDependencyInjectionComponent().inject(this);
    }*/

    public static FavoriteMoviesFragment newInstance() {
        return new FavoriteMoviesFragment();
    }


    @Override
    public Single<List<Movie>> getMovies() {
        return moviesBrowserUseCase.getPopular();
    }

    @Override
    protected void injectDependencies() {
        ((MoviesApplication) getActivity().getApplication()).getDependencyInjectionComponent().inject(this);
    }
}
