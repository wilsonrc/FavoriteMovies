package com.wilsonrc.favoritemovies.ui.moviedetails

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MovieDetailsPresenter @Inject constructor(private val moviesRepository: MoviesRepository) :
    MovieDetailsContract.Presenter<MovieDetailsContract.View> {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private var view: MovieDetailsContract.View? = null

    override fun loadMovieDetails(id: Int) {
        view?.showLoadingProgress()
        val disposable = moviesRepository.getMovieDetail(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<Movie>() {
                override fun onSuccess(movie: Movie) {
                    view?.hideLoadingProgress()
                    view?.showMovieDetails(movie)
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showMessage("Error", "Error while loading your favorite movies")
                    e.printStackTrace()
                }

            })
        disposables.add(disposable)
    }

    override fun attach(view: MovieDetailsContract.View) {
        this.view = view
    }

    override fun detach() {
        disposables.clear()
    }
}