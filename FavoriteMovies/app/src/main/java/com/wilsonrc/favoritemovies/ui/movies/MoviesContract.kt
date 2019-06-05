package com.wilsonrc.favoritemovies.ui.movies

import com.wilsonrc.favoritemovies.base.BasePresenter
import com.wilsonrc.favoritemovies.base.BaseView
import com.wilsonrc.favoritemovies.base.MessageShower
import com.wilsonrc.favoritemovies.base.ProgressLoader
import com.wilsonrc.favoritemovies.data.models.Movie

interface MoviesContract {

    interface View : BaseView, ProgressLoader, MessageShower {

        fun showMovies(movies: List<Movie>)
        fun showNoMovies()
        fun goToMovieDetal(movie: Movie)

    }

    interface Presenter<V : View> : BasePresenter<V> {

        fun loadMovies(forceFetch: Boolean)
        fun loadFavoriteMovies()
        fun saveFavMovie(movie: Movie)
        fun deleteFavMovie(movie: Movie)

    }

    interface Router {

        fun goToMovieDetail(movie: Movie)

    }

    interface MoviesOptionListener {

        fun onFavoriteClicked(movie: Movie)
        fun onMovieClicked(movie: Movie)

    }

}