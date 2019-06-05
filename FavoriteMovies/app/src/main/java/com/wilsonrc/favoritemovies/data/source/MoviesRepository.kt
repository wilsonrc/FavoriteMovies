package com.wilsonrc.favoritemovies.data.source

import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

class MoviesRepository : MoviesDataSource{

    override fun getMovies(): Observable<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavMovies(): Single<List<Movie>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveFavMovies(movie: Movie): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteFavMovies(id: Int): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}