package com.mobiversal.emanuelcristutiu.movieapp.ui.save

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.actor.ActorsRepository
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreRepository
import com.mobiversal.emanuelcristutiu.movieapp.movie.Movie
import com.mobiversal.emanuelcristutiu.movieapp.movie.MoviesRepository
import com.mobiversal.emanuelcristutiu.movieapp.movies_list.MoviesAdapter
import com.mobiversal.emanuelcristutiu.movieapp.ui.search.SearchMovieInteractionListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteMoviesFragment : Fragment(), SearchMovieInteractionListener {

    private val movieRepository = MoviesRepository.instance
    private var adapter: MoviesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getMovies()
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
    fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieRepository.getFavoriteMovies()
            withContext(Dispatchers.Main) {
                setupRecyclerView(movies)
            }
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        getMovies()
    }
    private fun setupRecyclerView(movies: List<Movie>) {
        iv_movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.adapter = MoviesAdapter(movies, this)
        iv_movie.adapter = adapter
    }
    override fun updateMovie(movie: Movie) {
        GlobalScope.launch{
            movieRepository.save(movie)
            withContext(Dispatchers.Main){
                adapter?.notifyDataSetChanged()
            }
        }
    }

}