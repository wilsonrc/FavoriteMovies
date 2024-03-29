package com.wilsonrc.favoritemovies.di.modules

import com.wilsonrc.favoritemovies.di.scope.ActivityScope
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsActivity
import com.wilsonrc.favoritemovies.ui.moviedetails.di.MovieDetailsActivityModule
import com.wilsonrc.favoritemovies.ui.movies.MoviesActivity
import com.wilsonrc.favoritemovies.ui.movies.di.MoviesActivityModule
import com.wilsonrc.favoritemovies.ui.search.SearchActivity
import com.wilsonrc.favoritemovies.ui.search.di.SearchActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MoviesActivityModule::class])
    abstract fun provideMoviesActivity(): MoviesActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MovieDetailsActivityModule::class])
    abstract fun provideMovieDetailsActivity(): MovieDetailsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SearchActivityModule::class])
    abstract fun provideSearchActivity(): SearchActivity

}