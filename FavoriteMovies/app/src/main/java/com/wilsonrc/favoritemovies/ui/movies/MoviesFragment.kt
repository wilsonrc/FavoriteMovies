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
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.view.*
import javax.inject.Inject

class MoviesFragment : DaggerFragment(), MoviesContract.View, MoviesContract.ActionListener {


    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    private var param1: String? = null
    private var param2: String? = null

    @Inject
    lateinit var presenter: MoviesContract.Presenter<MoviesContract.View>

    @Inject
    lateinit var router: MoviesContract.Router

    var adapter: MoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.attach(this@MoviesFragment)

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
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

        presenter.loadMovies()

        return rootView
    }

    companion object {
        fun newInstance() = MoviesFragment()
    }


//    companion object {
//
//        fun newInstance(param1: String, param2: String) =
//            MoviesFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

    override fun showMovies(movies: List<Movie>) {
        tvNoMovies?.visibility = View.GONE
        rvMovies?.visibility = View.VISIBLE
        adapter?.addData(movies)
    }

    override fun showNoMovies() {
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
        if (movie.isFavorite) {
            presenter.saveFavMovie(movie)
        } else {
            presenter.deleteFavMovie(movie)
        }
    }

    override fun onMovieClicked(movie: Movie) {
        router.goToMovieDetail(movie)
    }

}
