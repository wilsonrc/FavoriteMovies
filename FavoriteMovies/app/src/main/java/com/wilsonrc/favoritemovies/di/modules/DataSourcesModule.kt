package com.wilsonrc.favoritemovies.di.modules

import com.wilsonrc.favoritemovies.data.source.local.MoviesDao
import com.wilsonrc.favoritemovies.data.source.local.MoviesLocalDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesRemoteDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesService
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object DataSourcesModule {
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersRemoteDataSource(moviesService: MoviesService): MoviesRemoteDataSource {
        return MoviesRemoteDataSource(moviesService)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersLocalDataSource(moviesDao: MoviesDao): MoviesLocalDataSource {
        return MoviesLocalDataSource(moviesDao)
    }

}