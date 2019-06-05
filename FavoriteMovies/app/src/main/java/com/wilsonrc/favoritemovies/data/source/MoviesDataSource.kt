package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface MoviesDataSource {

    fun getMovies() : Observable<List<Movie>>

    fun getFavMovies() : Single<List<Movie>>

    fun saveFavMovies(movie: Movie) : Completable

    fun deleteFavMovies(movie: Movie) : Completable

}