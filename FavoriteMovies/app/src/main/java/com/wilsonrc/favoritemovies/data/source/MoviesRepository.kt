package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.local.MoviesLocalDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.functions.BiFunction
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesDataSource {

    override fun getMovies(forceFetch: Boolean): Observable<List<Movie>> {
        val allLocalMoviesObservable = moviesLocalDataSource.getMovies()
        val localFavoriteMoviesObservable = moviesLocalDataSource.getFavMovies().toObservable()

        val remoteMoviesObservable = moviesRemoteDataSource.getMovies()
            .zipWith(
                localFavoriteMoviesObservable,
                BiFunction<List<Movie>, List<Movie>, List<Movie>> { remoteMovies, localMovies ->
                    val favoriteIds = localMovies.map { it.id }
                    favoriteIds.forEach { localId ->
                        val movie = remoteMovies.find { remote -> remote.id == localId }
                        if (movie != null) {
                            movie.isFavorite = true
                        }
                    }
                    moviesLocalDataSource.saveMovies(remoteMovies).subscribe()

                    remoteMovies
                })

        return allLocalMoviesObservable.flatMap {
            Observable.just(it.isNotEmpty())
        }.flatMap {
            if (it && !forceFetch) {
                allLocalMoviesObservable
            } else {
                remoteMoviesObservable
            }
        }

    }

    override fun searchMovies(query: String): Single<List<Movie>> {
        return moviesRemoteDataSource.searchMovies(query)
    }

    override fun getMovieDetail(id: Int): Single<Movie> {
        return moviesRemoteDataSource.getMovieDetail(id)
    }

    override fun getFavMovies(): Single<List<Movie>> {
        return moviesLocalDataSource.getFavMovies()
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return moviesLocalDataSource.saveMovies(movies)
    }

    override fun saveFavMovie(movie: Movie): Completable {
        return moviesLocalDataSource.saveFavMovie(movie)
    }

    override fun deleteFavMovie(movie: Movie): Completable {
        return moviesLocalDataSource.deleteFavMovie(movie)
    }

}