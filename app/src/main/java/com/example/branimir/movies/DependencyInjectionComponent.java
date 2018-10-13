package com.example.branimir.movies;

import com.example.branimir.movies.repository.remote.RemoteRepositoryModule;
import com.example.branimir.movies.ui.activity.MovieDetailsActivity;
import com.example.branimir.movies.ui.fragment.FavoriteMoviesFragment;
import com.example.branimir.movies.ui.fragment.PopularMoviesFragment;
import com.example.branimir.movies.ui.fragment.TopRatedMoviesFragment;
import com.example.branimir.movies.usecase.UseCaseModule;
import javax.inject.Singleton;
import dagger.Component;

@Component(modules = {RemoteRepositoryModule.class, UseCaseModule.class})
@Singleton
public interface DependencyInjectionComponent {

    void inject(TopRatedMoviesFragment topRatedMoviesFragment);

    void inject(PopularMoviesFragment popularMoviesFragment);

    void inject(FavoriteMoviesFragment favoriteMoviesFragment);

    void inject(MovieDetailsActivity movieDetailsActivity);
}
