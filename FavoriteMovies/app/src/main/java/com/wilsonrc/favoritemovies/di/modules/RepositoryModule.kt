package com.wilsonrc.favoritemovies.di.modules

import com.wilsonrc.favoritemovies.data.source.MoviesRepository
import com.wilsonrc.favoritemovies.data.source.local.MoviesLocalDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersRepository(
        moviesRemoteDataSource: MoviesRemoteDataSource,
        moviesLocalDataSource: MoviesLocalDataSource
    ): MoviesRepository {
        return MoviesRepository(moviesRemoteDataSource, moviesLocalDataSource)
    }
    
}