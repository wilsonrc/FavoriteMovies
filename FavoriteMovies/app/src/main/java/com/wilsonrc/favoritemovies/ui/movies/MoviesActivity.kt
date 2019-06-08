package com.wilsonrc.favoritemovies.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import com.wilsonrc.favoritemovies.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_movies.*
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.wilsonrc.favoritemovies.ui.search.SearchActivity


class MoviesActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        setupUi()

        setupSearchView()
    }

    private fun setupSearchView() {
        searchView.setVoiceSearch(false)

        searchView?.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val intent = Intent(this@MoviesActivity, SearchActivity::class.java)
                intent.putExtra("SEARCH_QUERY", query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

    }

    private fun setupUi() {
        val adapter = TabAdapter(supportFragmentManager)
        setSupportActionBar(toolbar)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.wilsonrc.favoritemovies.R.menu.movies_menu, menu)

        val item = menu?.findItem(R.id.action_search)
        searchView?.setMenuItem(item)

        return true
    }
}
