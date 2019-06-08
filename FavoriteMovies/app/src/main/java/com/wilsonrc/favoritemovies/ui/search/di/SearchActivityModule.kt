package com.wilsonrc.favoritemovies.ui.search.di

import com.wilsonrc.favoritemovies.di.scope.FragmentScope
import com.wilsonrc.favoritemovies.ui.search.SearchResultsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchActivityModule {

    @ContributesAndroidInjector(modules = [SearchResultsFragmentModule::class])
    @FragmentScope
    abstract fun providSearchResultsFragment(): SearchResultsFragment

}