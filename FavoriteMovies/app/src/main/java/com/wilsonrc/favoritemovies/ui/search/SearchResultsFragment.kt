package com.wilsonrc.favoritemovies.ui.search

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.data.models.Movie
import com.wilsonrc.favoritemovies.ui.movies.MoviesContract
import com.wilsonrc.favoritemovies.utils.DisplayTools
import com.wilsonrc.favoritemovies.utils.gone
import com.wilsonrc.favoritemovies.utils.visible
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies_content.*
import kotlinx.android.synthetic.main.fragment_movies_content.view.*
import javax.inject.Inject

class SearchResultsFragment : DaggerFragment(), SearchContract.View, SearchContract.ActionListener {

    private val SEARCH_QUERY = "SEARCH_QUERY"

    @Inject
    lateinit var presenter: SearchContract.Presenter<SearchContract.View>

    @Inject
    lateinit var router: SearchContract.Router

    private var currentQuery : String = ""

    private var adapter: SearchResultsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attach(this@SearchResultsFragment)

        arguments?.let {
            currentQuery = it.getString(SEARCH_QUERY, "")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)

        adapter = SearchResultsAdapter(activity as Context, mutableListOf(), this@SearchResultsFragment)
        if (DisplayTools.isTabletDisplay(activity as Activity)) {
            rootView?.rvMovies?.layoutManager = GridLayoutManager(activity, 4)
        } else {
            rootView?.rvMovies?.layoutManager = GridLayoutManager(activity, 2)
        }
        rootView?.rvMovies?.adapter = adapter


        if(currentQuery.isNotEmpty()){
            presenter.loadSearchResults(currentQuery)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer?.setOnRefreshListener {
            adapter?.resetData()

            if(currentQuery.isNotEmpty()){
                presenter.loadSearchResults(currentQuery)
            }

        }
        swipeContainer?.setColorSchemeResources(
            R.color.accent,
            R.color.colorPrimaryDark
        )
    }


    companion object {

        fun newInstance(query: String) =
            SearchResultsFragment().apply {
                arguments = Bundle().apply {
                    putString(SEARCH_QUERY, query)
                }
            }

    }

    override fun showResults(movies: List<Movie>) {
        tvNoMovies?.gone()
        rvMovies?.visible()
        adapter?.replaceData(movies)
    }

    override fun showNoResults() {
        swipeContainer.isRefreshing = false
        rvMovies?.gone()
        tvNoMovies?.visible()
    }

    override fun showLoadingProgress() {
        progressBar?.visible()
    }

    override fun hideLoadingProgress() {
        progressBar?.gone()
    }

    override fun showMessage(title: String, body: String) {
        Toast.makeText(activity, "$title: $body", Toast.LENGTH_LONG).show()
    }

    override fun onFavoriteClicked(movie: Movie) {
        if (!movie.isFavorite) {
            presenter.saveFavMovie(movie)
        } else {
            presenter.deleteFavMovie(movie)
        }
    }

    override fun onMovieClicked(movie: Movie) {
        router.goToMovieDetails(movie)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

}