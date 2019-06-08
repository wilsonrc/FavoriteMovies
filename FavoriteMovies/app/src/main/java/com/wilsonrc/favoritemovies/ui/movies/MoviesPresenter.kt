package com.wilsonrc.favoritemovies.ui.movies

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesPresenter @Inject constructor(private val moviesRepository: MoviesRepository) :
    MoviesContract.Presenter<MoviesContract.View> {

    private var disposables: CompositeDisposable = CompositeDisposable()
    private var view: MoviesContract.View? = null

    override fun loadMovies(forceFetch: Boolean) {
        view?.showLoadingProgress()
        val disposable = moviesRepository.getMovies(forceFetch)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Movie>>() {
                override fun onComplete() {
                }

                override fun onNext(movies: List<Movie>) {
                    view?.hideLoadingProgress()
                    view?.showMovies(movies.sortedByDescending { it.releaseDate })
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showMessage("Error", "Error while loading list of movies")
                    view?.showNoMovies()
                    e.printStackTrace()
                }
            })

        disposables.add(disposable)
    }

    override fun loadFavoriteMovies() {
        view?.showLoadingProgress()
        val disposable = moviesRepository.getFavMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Movie>>() {
                override fun onSuccess(movies: List<Movie>) {
                    view?.hideLoadingProgress()
                    if (movies.isNotEmpty()) {
                        view?.showMovies(movies)
                    } else {
                        view?.showNoMovies()
                    }
                }

                override fun onError(e: Throwable) {
                    view?.hideLoadingProgress()
                    view?.showMessage("Error", "Error while loading your favorite movies")
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

    override fun attach(view: MoviesContract.View) {
        this.view = view
    }

    override fun detach() {
        disposables.clear()
        view = null
    }

}