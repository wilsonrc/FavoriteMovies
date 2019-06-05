package com.wilsonrc.favoritemovies.di.modules

import androidx.room.Room
import com.wilsonrc.favoritemovies.base.BaseApp
import com.wilsonrc.favoritemovies.data.source.local.MoviesDao
import com.wilsonrc.favoritemovies.data.source.local.MoviesDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideGithubUsersDatabase(app: BaseApp): MoviesDatabase = Room.databaseBuilder(app,
        MoviesDatabase::class.java, "movies_db").build()

    @Provides
    @JvmStatic
    internal fun provideMoviesDao(database: MoviesDatabase): MoviesDao = database.moviesDao()

}