package com.wilsonrc.favoritemovies.ui.moviedetails

import com.wilsonrc.favoritemovies.base.BasePresenter
import com.wilsonrc.favoritemovies.base.BaseView
import com.wilsonrc.favoritemovies.base.MessageShower
import com.wilsonrc.favoritemovies.base.ProgressLoader
import com.wilsonrc.favoritemovies.data.models.Movie

interface MovieDetailsContract {

    interface View : BaseView, ProgressLoader, MessageShower {

        fun showMovieDetails(movies: Movie)
        fun showNoMovieDetails()

    }

    interface Presenter<V : MovieDetailsContract.View> : BasePresenter<V> {

        fun loadMovieDetails(id: Int)

    }

    interface Router {


    }

}