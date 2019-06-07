package com.wilsonrc.favoritemovies.data.source.remote

import com.wilsonrc.favoritemovies.data.models.DiscoverResponse
import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    fun getAllMovies(@Query("sort") sortBy : String , @Query("year")year : String) : Observable<DiscoverResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int) : Single<Movie>

}