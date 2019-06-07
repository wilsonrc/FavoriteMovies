package com.wilsonrc.favoritemovies.data.source.remote

import com.wilsonrc.favoritemovies.BuildConfig
import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesService: MoviesService) : MoviesDataSource {

    override fun getMovies(): Observable<List<Movie>> {
        return moviesService.getAllMovies("release_date.desc", "2019", BuildConfig.API_KEY)
            .flatMap {
                Observable.just(it.movies)
            }
    }

    override fun getFavMovies(): Single<List<Movie>> {
        throw Exception("Get Fav Movies From Remote is not Allowed.")
    }

    override fun saveFavMovies(movie: Movie): Completable {
        throw Exception("Save Fav Movies to Remote is not Allowed.")
    }

    override fun deleteFavMovies(movie: Movie): Completable {
        throw Exception("Delete Fav Movies from Remote is not Allowed.")
    }
}