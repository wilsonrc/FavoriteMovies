package com.wilsonrc.favoritemovies.ui.movies.di

import com.wilsonrc.favoritemovies.di.scope.FragmentScope
import com.wilsonrc.favoritemovies.ui.movies.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityModule {

    @ContributesAndroidInjector(modules = [MoviesFragmentModule::class])
    @FragmentScope
    abstract fun provideMoviesFragment(): MoviesFragment



}