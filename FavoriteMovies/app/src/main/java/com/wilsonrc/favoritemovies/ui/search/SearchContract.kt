package com.wilsonrc.favoritemovies.ui.search

import com.wilsonrc.favoritemovies.base.BasePresenter
import com.wilsonrc.favoritemovies.base.BaseView
import com.wilsonrc.favoritemovies.base.MessageShower
import com.wilsonrc.favoritemovies.base.ProgressLoader
import com.wilsonrc.favoritemovies.data.models.Movie

interface SearchContract {

    interface View : BaseView, ProgressLoader, MessageShower {

        fun showResults(movies: List<Movie>)
        fun showNoResults()

    }

    interface Presenter<V : View> : BasePresenter<SearchContract.View> {

        fun loadSearchResults(query: String)
        fun saveFavMovie(movie: Movie)
        fun deleteFavMovie(movie: Movie)

    }

    interface Router {

        fun goToMovieDetails(movie: Movie)

    }

    interface ActionListener {

        fun onFavoriteClicked(movie: Movie)
        fun onMovieClicked(movie: Movie)

    }

}