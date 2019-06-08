package com.wilsonrc.favoritemovies.ui.search.di

import com.wilsonrc.favoritemovies.di.scope.FragmentScope
import com.wilsonrc.favoritemovies.ui.movies.MoviesContract
import com.wilsonrc.favoritemovies.ui.movies.MoviesPresenter
import com.wilsonrc.favoritemovies.ui.movies.MoviesRouter
import com.wilsonrc.favoritemovies.ui.search.SearchContract
import com.wilsonrc.favoritemovies.ui.search.SearchPresenter
import com.wilsonrc.favoritemovies.ui.search.SearchRouter
import dagger.Binds
import dagger.Module

@Module
abstract class SearchResultsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun provideSearchPresenter(searchPresenter: SearchPresenter): SearchContract.Presenter<SearchContract.View>

    @Binds
    @FragmentScope
    abstract fun provideMoviesRouter(searchRouter: SearchRouter): SearchContract.Router

}