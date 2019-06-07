package com.wilsonrc.favoritemovies.ui.moviedetails

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.data.models.Movie
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movie_details.*
import javax.inject.Inject

class MovieDetailsActivity : DaggerAppCompatActivity(), MovieDetailsContract.View {

    @Inject
    lateinit var presenter: MovieDetailsContract.Presenter<MovieDetailsContract.View>

    @Inject
    lateinit var router: MovieDetailsContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        presenter.attach(this@MovieDetailsActivity)
        val id = intent.extras.getInt("MOVIE_ID")
        presenter.loadMovieDetails(id)
    }

    override fun showMovieDetails(movie: Movie) {
        Glide.with(this@MovieDetailsActivity).load("http://image.tmdb.org/t/p/w780/${movie.backdropPath}")
            .into(ivBackDropPoster)
        Glide.with(this@MovieDetailsActivity).load("http://image.tmdb.org/t/p/w185/${movie.posterPath}").into(ivPoster)
        tvTitle.text = movie.title
        ratingBar.rating = (movie.voteAverage?.toFloat()?.div(2)) ?: 0.0f
        tvReleaseDate.text = movie.releaseDate
        tvOverview.text = movie.overview
    }

    override fun showNoMovieDetails() {

    }

    override fun showLoadingProgress() {
    }

    override fun hideLoadingProgress() {
    }

    override fun showMessage(title: String, body: String) {
        Toast.makeText(this@MovieDetailsActivity, "$title: $body", Toast.LENGTH_LONG).show()
    }
}
