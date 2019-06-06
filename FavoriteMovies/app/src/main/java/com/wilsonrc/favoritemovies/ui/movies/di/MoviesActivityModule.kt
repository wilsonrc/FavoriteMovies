package com.wilsonrc.favoritemovies.ui.movies.di

import com.wilsonrc.favoritemovies.di.scope.ActivityScope
import com.wilsonrc.favoritemovies.di.scope.FragmentScope
import com.wilsonrc.favoritemovies.ui.movies.MoviesContract
import com.wilsonrc.favoritemovies.ui.movies.MoviesFragment
import com.wilsonrc.favoritemovies.ui.movies.MoviesPresenter
import com.wilsonrc.favoritemovies.ui.movies.MoviesRouter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityModule {

    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideMoviesFragment(): MoviesFragment

    @Binds
    @ActivityScope
    internal abstract fun provideMoviesPresenter(moviesPresenter: MoviesPresenter) : MoviesContract.Presenter<MoviesContract.View>

    @Binds
    @ActivityScope
    internal abstract fun provideMoviesRouter(moviesRouter: MoviesRouter) : MoviesContract.Router

}