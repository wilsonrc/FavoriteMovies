package com.wilsonrc.favoritemovies.ui.search

import android.content.Intent
import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.ui.moviedetails.MovieDetailsActivity
import javax.inject.Inject

class SearchRouter@Inject constructor(private val activity: SearchActivity) : SearchContract.Router {

    override fun goToMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra("MOVIE_ID", movie.id)
        activity.startActivity(intent)
    }

}