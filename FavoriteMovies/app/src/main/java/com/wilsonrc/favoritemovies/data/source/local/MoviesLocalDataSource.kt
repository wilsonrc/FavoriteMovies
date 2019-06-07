package com.wilsonrc.favoritemovies.data.source.local

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(private val moviesDao: MoviesDao) : MoviesDataSource {

    override fun getMovies(): Observable<List<Movie>> {
        return moviesDao.getAllMovies()
            .toObservable()
    }

    override fun getMovieDetail(id: Int): Single<Movie> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavMovies(): Single<List<Movie>> {
       return moviesDao.getFavoriteMovies()
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return Completable.fromAction{
            moviesDao.saveAll(movies)
        }
    }

    override fun saveFavMovie(movie: Movie): Completable {
        return Completable.fromAction{
            movie.isFavorite = true
            moviesDao.update(movie)
        }
    }

    override fun deleteFavMovie(movie: Movie): Completable {
        return Completable.fromAction{
            movie.isFavorite = false
            moviesDao.update(movie)
        }
    }
}