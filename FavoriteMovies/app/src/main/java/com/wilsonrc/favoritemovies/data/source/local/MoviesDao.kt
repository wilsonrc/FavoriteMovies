package com.wilsonrc.favoritemovies.data.source.local

import androidx.room.*
import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Single


@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies")
    fun getAllMovies() : Single<List<Movie>>

    @Query("SELECT * FROM Movies where isFavorite = 1")
    fun getFavoriteMovies(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAll(movies: List<Movie>)

    @Update
    fun update(movie : Movie) : Int

    @Query("DELETE FROM Movies WHERE id = :id")
    fun deleteMovie(id: Int): Int

}