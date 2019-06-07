package com.wilsonrc.favoritemovies.ui.movies

import android.content.Intent
import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsActivity
import javax.inject.Inject

class MoviesRouter @Inject constructor(private val activity: MoviesActivity) : MoviesContract.Router {

    override fun goToMovieDetail(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movie.id)
        activity.startActivity(intent)
    }

}