package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MoviesDataSource {

    fun getMovies() : Observable<List<Movie>>

    fun getMovieDetail(id : Int) : Single<Movie>

    fun getFavMovies() : Single<List<Movie>>

    fun saveMovies(movies: List<Movie>) : Completable

    fun saveFavMovie(movie: Movie) : Completable

    fun deleteFavMovie(movie: Movie) : Completable

}