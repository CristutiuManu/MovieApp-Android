package com.mobiversal.emanuelcristutiu.movieapp.movies_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiversal.emanuelcristutiu.movieapp.Constants.KEY_MOVIE_ID
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.movie.Movie
import com.mobiversal.emanuelcristutiu.movieapp.movie.MovieDetailsActivity
import com.mobiversal.emanuelcristutiu.movieapp.ui.search.SearchMovieInteractionListener
import com.mobiversal.emanuelcristutiu.movieapp.utils.ImageLoader
import kotlinx.android.synthetic.main.fragment_search.view.*
import kotlinx.android.synthetic.main.movies_list_item.view.*
import kotlinx.android.synthetic.main.movies_list_item.view.iv_movie

class MoviesAdapter(private val moviesList: List<Movie>?, private val searchMovieInteractionListener: SearchMovieInteractionListener)
    : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movies_list_item, parent, false))
    }


    override fun getItemCount(): Int {
        return moviesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie: Movie? = moviesList?.get(position)
        movie?.let {
            holder.bind(movie)
        }
    }
    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            val posterPath = movie.poster_path
            if (!posterPath.isNullOrEmpty()) {
                ImageLoader.loadImage(
                    "https://image.tmdb.org/t/p/original" + posterPath,
                    itemView.iv_movie,
                    itemView.context
                )
            }

            var movieHeaderText = movie.title
            if (!movie.release_date.isNullOrEmpty()) movieHeaderText += " (${movie.release_date})"
            itemView.tv_movie_name_year.text = movieHeaderText

            //itemView.tv_movie_name_year.text = movie.title + " " + "(" + movie.release_date + ")"
            if (movie.overview.length >= 80)
                itemView.tv_describe_movie.text = movie.overview.substring(0, 80) + "..."
            else
                itemView.tv_describe_movie.text = movie.overview + "..."

            itemView.button_add_favorite.setOnClickListener {
                movie.isFavorite = !(movie.isFavorite ?: false)
                searchMovieInteractionListener.updateMovie(movie)
            }
            itemView.button_add_watched.setOnClickListener {
                movie.isWatched = !(movie.isWatched ?: false)
                searchMovieInteractionListener.updateMovie(movie)
            }

            if (movie.isFavorite == true) {
                itemView.button_add_favorite.text = "Remove favorite"
            }
            else itemView.button_add_favorite.text= "Add favorite"

            if(movie.isWatched == true) {
                itemView.button_add_watched.text = "Remove watched"
            }
            else itemView.button_add_watched.text = "Add watched"

            itemView.rootView.setOnClickListener {
                openMoviesDetailActivity(movie, itemView.context)
            }
        }
    }

    private fun openMoviesDetailActivity(movie: Movie, context: Context) {

        val intent = Intent(context, MovieDetailsActivity::class.java)
        val bundle = Bundle()
        bundle.putInt(KEY_MOVIE_ID, movie.id)
        intent.putExtras(bundle)
        context.startActivity(intent)
    }

}



