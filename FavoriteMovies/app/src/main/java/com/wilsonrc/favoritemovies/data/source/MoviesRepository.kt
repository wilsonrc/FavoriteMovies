package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.local.MoviesLocalDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesDataSource {

    override fun getMovies(): Observable<List<Movie>> {
        val local = moviesLocalDataSource.getFavMovies().toObservable()
        return moviesRemoteDataSource.getMovies()
            .zipWith(local, BiFunction<List<Movie>, List<Movie>, List<Movie>> { remoteMovies, localMovies ->
                val favoriteIds = localMovies.map { it.id }
                favoriteIds.forEach { localId ->
                    val movie = remoteMovies.find { remote -> remote.id == localId }
                    if (movie != null) {
                        movie.isFavorite = true
                    }
                }
                remoteMovies
            })
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