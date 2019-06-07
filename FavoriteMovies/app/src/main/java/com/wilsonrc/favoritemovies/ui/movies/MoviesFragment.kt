package com.wilsonrc.favoritemovies.ui.movies

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
import com.wilsonrc.favoritemovies.utils.DisplayTools
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movies_content.*
import kotlinx.android.synthetic.main.fragment_movies_content.view.*
import javax.inject.Inject

class MoviesFragment : DaggerFragment(), MoviesContract.View, MoviesContract.ActionListener {

    private val DATA_TYPE = "DATA_TYPE"

    private var isGeneral: Boolean = false

    @Inject
    lateinit var presenter: MoviesContract.Presenter<MoviesContract.View>

    @Inject
    lateinit var router: MoviesContract.Router

    var adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attach(this@MoviesFragment)

        arguments?.let {
            isGeneral = "GENERAL" == it.getString(DATA_TYPE, "GENERAL")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_movies, container, false)

        adapter = MoviesAdapter(activity as Context, mutableListOf(), this@MoviesFragment)
        if (DisplayTools.isTabletDisplay(activity as Activity)) {
            rootView?.rvMovies?.layoutManager = GridLayoutManager(activity, 4)
        } else {
            rootView?.rvMovies?.layoutManager = GridLayoutManager(activity, 2)
        }
        rootView?.rvMovies?.adapter = adapter

        if (isGeneral) {
            presenter.loadMovies()
        } else {
            presenter.loadFavoriteMovies()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeContainer?.setOnRefreshListener {
            if (isGeneral) {
                adapter?.resetData()
                presenter.loadMovies()
            } else {
                adapter?.resetData()
                presenter.loadFavoriteMovies()
            }
        }
        swipeContainer?.setColorSchemeResources(
            R.color.accent,
            R.color.colorPrimaryDark
        )
    }

    companion object {

        fun newInstance(typeOfData: String) =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(DATA_TYPE, typeOfData)
                }
            }
    }


    override fun showMovies(movies: List<Movie>) {
        swipeContainer.isRefreshing = false
        tvNoMovies?.visibility = View.GONE
        rvMovies?.visibility = View.VISIBLE
        adapter?.addData(movies)
    }

    override fun showNoMovies() {
        swipeContainer.isRefreshing = false
        rvMovies?.visibility = View.GONE
        tvNoMovies?.visibility = View.VISIBLE
    }

    override fun showLoadingProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBar?.visibility = View.GONE
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
        router.goToMovieDetail(movie)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}
