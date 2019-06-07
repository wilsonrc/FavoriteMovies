package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.local.MoviesLocalDataSource
import com.wilsonrc.favoritemovies.data.source.remote.MoviesRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesDataSource {

    override fun getMovies(): Observable<List<Movie>> {
        return moviesRemoteDataSource.getMovies()
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