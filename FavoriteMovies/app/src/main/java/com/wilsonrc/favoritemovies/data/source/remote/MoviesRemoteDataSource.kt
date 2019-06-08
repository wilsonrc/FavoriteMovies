package com.wilsonrc.favoritemovies.data.source.remote

import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.data.source.MoviesDataSource
import com.wilsonrc.favoritemovies.utils.DateTool
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val moviesService: MoviesService) : MoviesDataSource {

    override fun getMovieDetail(id: Int): Single<Movie> {
        return moviesService.getDetails(id)
    }

    override fun getMovies(forceFetch : Boolean): Observable<List<Movie>> {
        return moviesService.getAll(
            "release_date.desc",
            DateTool.getCurrentYear().toString()
        )
            .flatMap {
                Observable.just(it.movies)
            }
    }

    override fun searchMovies(query: String): Single<List<Movie>> {
        return moviesService.search(query).flatMap {
            Single.just(it.movies)
        }
    }

    override fun saveMovies(movies: List<Movie>): Completable {
        throw Exception("Save Movies to Remote is not Allowed.")
    }

    override fun getFavMovies(): Single<List<Movie>> {
        throw Exception("Get Fav Movies From Remote is not Allowed.")
    }

    override fun saveFavMovie(movie: Movie): Completable {
        throw Exception("Save Fav Movies to Remote is not Allowed.")
    }

    override fun deleteFavMovie(movie: Movie): Completable {
        throw Exception("Delete Fav Movies from Remote is not Allowed.")
    }

}