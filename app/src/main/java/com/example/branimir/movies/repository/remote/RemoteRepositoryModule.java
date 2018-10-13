package com.example.branimir.movies.repository.remote;

import com.example.branimir.movies.repository.remote.httpclient.HttpClient;
import com.example.branimir.movies.repository.remote.httpclient.RetrofitClient;
import com.example.branimir.movies.repository.remote.httpclient.remoteDao.RemoteDaoImpl;
import com.example.branimir.movies.usecase.dependency.remote.RemoteDao;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class RemoteRepositoryModule {

    @Provides
    @Singleton
    RemoteDao providesRemoteCategoryDao(HttpClient client) {
        return new RemoteDaoImpl(client);
    }

    @Provides
    @Singleton
    HttpClient providesHttpClient() {
        return new RetrofitClient();
    }

}
