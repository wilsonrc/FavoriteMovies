package com.wilsonrc.favoritemovies.ui.search

import android.os.Bundle
import android.view.Menu
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.utils.replaceFragmentSafely
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movies.*

class SearchActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupUi()

        setupEvents()

    }

    private fun setupUi() {
        toolbar.title = "Search Movies"
        setSupportActionBar(toolbar)

        searchView.setVoiceSearch(false)
        val searchQuery = intent?.extras?.getString("SEARCH_QUERY") ?: ""

        searchView.showSearch(false)
        searchView.setQuery(searchQuery, true)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.wilsonrc.favoritemovies.R.menu.movies_menu, menu)

        val item = menu?.findItem(R.id.action_search)
        searchView?.setMenuItem(item)

        return true
    }


    private fun setupEvents() {
        searchView?.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val searchResultFragment = SearchResultsFragment.newInstance(query)
                replaceFragmentSafely(searchResultFragment, "SearchResultsFragment", true, R.id.fragmentContainer)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                //Do some magic
                return false
            }
        })

    }
}
