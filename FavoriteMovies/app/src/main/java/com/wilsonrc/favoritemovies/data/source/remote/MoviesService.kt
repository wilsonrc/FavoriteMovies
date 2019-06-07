package com.wilsonrc.favoritemovies.data.source.remote

import com.wilsonrc.favoritemovies.data.models.DiscoverResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("discover/movie")
    fun getAllMovies(@Query("sort") sortBy : String , @Query("year")year : String ,  @Query("api_key") apiKey :String) : Observable<DiscoverResponse>

}