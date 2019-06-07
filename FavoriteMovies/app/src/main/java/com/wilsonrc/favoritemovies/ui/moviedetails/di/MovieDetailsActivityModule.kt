package com.wilsonrc.favoritemovies.ui.moviedetails.di

import com.wilsonrc.favoritemovies.di.scope.ActivityScope
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsContract
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsPresenter
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsRouter
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailsActivityModule {

    @Binds
    @ActivityScope
    abstract fun provideMoviesPresenter(moviesDetailsPresenter: MovieDetailsPresenter): MovieDetailsContract.Presenter<MovieDetailsContract.View>

    @Binds
    @ActivityScope
    abstract fun provideMoviesRouter(moviesDetailsRouter: MovieDetailsRouter): MovieDetailsContract.Router

}