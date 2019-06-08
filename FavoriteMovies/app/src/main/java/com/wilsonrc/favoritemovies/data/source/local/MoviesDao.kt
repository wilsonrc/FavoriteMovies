package com.wilsonrc.favoritemovies.data.source.local

import androidx.room.*
import com.wilsonrc.favoritemovies.data.models.Movie
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM Movies ORDER BY release_date DESC")
    fun getAllMovies(): Single<List<Movie>>

    @Query("DELETE FROM Movies WHERE isFavorite = 0")
    fun deleteCachedMovies() : Int

    @Query("SELECT * FROM Movies WHERE title LIKE :query")
    fun searchMovies(query: String): Single<List<Movie>>

    @Query("SELECT * FROM Movies where isFavorite = 1")
    fun getFavoriteMovies(): Single<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun saveAll(movies: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun save(movies: Movie): Long

    @Update
    fun update(movie: Movie): Int

    @Query("DELETE FROM Movies WHERE id = :id")
    fun deleteMovie(id: Int): Int

}