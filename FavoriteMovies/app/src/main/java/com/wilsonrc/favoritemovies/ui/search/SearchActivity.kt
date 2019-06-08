package com.wilsonrc.favoritemovies.ui.search

import android.os.Bundle
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.utils.replaceFragmentSafely
import dagger.android.support.DaggerAppCompatActivity

class SearchActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchQuery = intent?.extras?.getString("SEARCH_QUERY") ?: ""

        val moviesFragment = SearchResultsFragment.newInstance(searchQuery)

        replaceFragmentSafely(moviesFragment, "GeneralMoviesFragment", true, R.id.fragmentContainer)

    }
}
