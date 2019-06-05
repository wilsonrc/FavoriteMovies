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

    override fun getFavMovies(): Single<List<Movie>> {
        return moviesLocalDataSource.getFavMovies()
    }

    override fun saveFavMovies(movie: Movie): Completable {
        return moviesLocalDataSource.saveFavMovies(movie)
    }

    override fun deleteFavMovies(movie: Movie): Completable {
        return moviesLocalDataSource.deleteFavMovies(movie)
    }

}