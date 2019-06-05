package com.wilsonrc.favoritemovies.di.modules

import com.wilsonrc.favoritemovies.ui.movies.MoviesActivity
import com.wilsonrc.favoritemovies.ui.movies.di.MoviesActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBindingModule {

    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun provideMoviesActivity(): MoviesActivity

}