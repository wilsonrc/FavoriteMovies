package com.wilsonrc.favoritemovies.ui.movies

import android.os.Bundle
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.utils.replaceFragmentSafely
import dagger.android.support.DaggerAppCompatActivity

class MoviesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        val moviesFragment = MoviesFragment.newInstance()

        replaceFragmentSafely(moviesFragment, "GeneralMoviesFragment", true, R.id.fragmentContainer)

    }
}
