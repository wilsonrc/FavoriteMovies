package com.wilsonrc.favoritemovies.di.modules

import com.wilsonrc.favoritemovies.data.source.remote.MoviesService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module
object ApiServiceModule {

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideUsersService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }

}