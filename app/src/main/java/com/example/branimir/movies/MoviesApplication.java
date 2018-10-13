package com.example.branimir.movies;

import android.app.Application;
import com.example.branimir.movies.repository.remote.RemoteRepositoryModule;
import com.example.branimir.movies.usecase.UseCaseModule;

public class MoviesApplication extends Application {

    private DependencyInjectionComponent dependencyInjectionComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        dependencyInjectionComponent = DaggerDependencyInjectionComponent.builder()
                .remoteRepositoryModule(new RemoteRepositoryModule())
                .useCaseModule(new UseCaseModule())
                .build();
    }

    public DependencyInjectionComponent getDependencyInjectionComponent() {
        return dependencyInjectionComponent;
    }
}
