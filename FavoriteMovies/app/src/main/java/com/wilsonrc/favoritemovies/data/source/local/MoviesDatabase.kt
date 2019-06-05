package com.wilsonrc.favoritemovies.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wilsonrc.favoritemovies.data.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

}