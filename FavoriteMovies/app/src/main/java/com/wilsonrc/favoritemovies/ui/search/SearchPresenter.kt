package com.wilsonrc.favoritemovies.ui.search

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPresenter @Inject constructor(private val moviesRepository: MoviesRepository) : SearchContract.Presenter<SearchContract.View> {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private var view: SearchContract.View? = null

    override fun loadSearchResults(query: String) {
        view?.showLoadingProgress()
        val disposable = moviesRepository.searchMovies(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(results: List<Movie>) {
                    view?.hideLoadingProgress()
                    view?.showResults(results)
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showNoResults()
                    e.printStackTrace()
                }
            })

        disposables.add(disposable)
    }

    override fun saveFavMovie(movie: Movie) {
        val disposable = moviesRepository.saveFavMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    view?.showMessage("Information", "Movie successfully saved to favorites.")
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showMessage("Error", "Error while saving this movie to favorites.")
                    e.printStackTrace()
                }
            })
        disposables.add(disposable)
    }

    override fun deleteFavMovie(movie: Movie) {
        val disposable = moviesRepository.deleteFavMovie(movie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    view?.showMessage("Information", "Movie successfully deleted from favorites.")
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showMessage("Error", "Error while deleting this movie from favorites.")
                    e.printStackTrace()
                }
            })
        disposables.add(disposable)
    }

    override fun attach(view: SearchContract.View) {
        this.view = view
    }

    override fun detach() {
        disposables.clear()
        view = null
    }

}