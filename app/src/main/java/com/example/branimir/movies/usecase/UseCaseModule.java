package com.example.branimir.movies.usecase;

import com.example.branimir.movies.usecase.dependency.remote.RemoteDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    @Singleton
    MoviesBrowserUseCase providesMoviesBrowserUsecase(RemoteDao remoteDao) {
        return new MoviesBrowserUseCase(remoteDao);
    }
}
