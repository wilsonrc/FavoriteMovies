package com.wilsonrc.favoritemovies.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wilsonrc.favoritemovies.R
import com.wilsonrc.favoritemovies.data.models.Movie
import kotlinx.android.synthetic.main.movies_item.view.*

class SearchResultsAdapter(
    private val context: Context,
    private val movies: MutableList<Movie>,
    private val listener: SearchContract.ActionListener
) : RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movies_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: SearchResultsAdapter.ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bindMovie(context, movie, position, listener)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindMovie(context: Context?, movie: Movie, position: Int, listener: SearchContract.ActionListener) {

            itemView.tvTitle?.text = movie.title
            itemView.rbScore?.rating = (movie.voteAverage?.toFloat()?.div(2)) ?: 0.0f
            context?.let {
                Glide.with(context).load("http://image.tmdb.org/t/p/w185/${movie.posterPath}").into(itemView.ivPoster)
                if (movie.isFavorite) {
                    itemView.btnFav?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_star_yellow_24dp
                        )
                    )
                } else {
                    itemView.btnFav?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.ic_star_border_yellow_24dp
                        )
                    )
                }
            }

            itemView.btnFav?.setOnClickListener {
                listener.onFavoriteClicked(movie)
                movie.isFavorite = !movie.isFavorite
                notifyItemChanged(position)
            }

            itemView.setOnClickListener {
                listener.onMovieClicked(movie)
            }

        }

    }

    fun resetData() {
        this.movies.clear()
        notifyDataSetChanged()
    }

    fun replaceData(users: List<Movie>) {
        movies.clear()
        movies.addAll(users)
        notifyDataSetChanged()
    }

    fun addData(users: List<Movie>) {
        val initPosition = movies.size
        movies.addAll(users)
        notifyItemRangeInserted(initPosition, movies.size)
    }

}