package com.wilsonrc.favoritemovies.ui.movies.di

import com.wilsonrc.favoritemovies.di.scope.FragmentScope
import com.wilsonrc.favoritemovies.ui.movies.MoviesContract
import com.wilsonrc.favoritemovies.ui.movies.MoviesPresenter
import com.wilsonrc.favoritemovies.ui.movies.MoviesRouter
import dagger.Binds
import dagger.Module

@Module
abstract class MoviesFragmentModule{

    @Binds
    @FragmentScope
    abstract fun provideMoviesPresenter(moviesPresenter: MoviesPresenter): MoviesContract.Presenter<MoviesContract.View>

    @Binds
    @FragmentScope
    abstract fun provideMoviesRouter(moviesRouter: MoviesRouter): MoviesContract.Router

}