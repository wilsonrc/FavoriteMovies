package com.wilsonrc.favoritemovies.data.source.local

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(private val moviesDao: MoviesDao) : MoviesDataSource {


    override fun searchMovies(query: String): Single<List<Movie>> {
        return moviesDao.searchMovies(query)
    }

    override fun getMovies(forceFetch : Boolean): Observable<List<Movie>> {
        return moviesDao.getAllMovies()
            .toObservable()
    }

    override fun getMovieDetail(id: Int): Single<Movie> {
        throw Exception("Get Movie Details from local Source is not Allowed.")
    }


    override fun getFavMovies(): Single<List<Movie>> {
       return moviesDao.getFavoriteMovies()
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        return Completable.fromAction{
            for(currentMovie in movies){
                saveOrUpdate(currentMovie)
            }
        }
    }

    override fun saveFavMovie(movie: Movie): Completable {
        return Completable.fromAction{
            movie.isFavorite = true
           saveOrUpdate(movie)
        }
    }

    private fun saveOrUpdate(movie: Movie) {
        val id = moviesDao.save(movie)
        if (id == (-1).toLong()) {
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