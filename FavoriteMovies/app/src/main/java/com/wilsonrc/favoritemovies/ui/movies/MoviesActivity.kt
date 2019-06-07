package com.wilsonrc.favoritemovies.ui.movies

import android.os.Bundle
import com.wilsonrc.favoritemovies.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movies.*


class MoviesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

//        replaceFragmentSafely(moviesFragment, "GeneralMoviesFragment", true, R.id.fragmentContainer)

        val adapter = TabAdapter(supportFragmentManager)
//        adapter.addFragment(MoviesFragment.newInstance("GENERAL"), "General")
//        adapter.addFragment(FavoriteFragments.newInstance("FAVORITES"), "Favorites")
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }
}
