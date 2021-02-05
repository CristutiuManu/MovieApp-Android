package com.mobiversal.emanuelcristutiu.movieapp.ui.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.actor.ActorsRepository
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreRepository
import com.mobiversal.emanuelcristutiu.movieapp.movie.Movie
import com.mobiversal.emanuelcristutiu.movieapp.movie.MoviesRepository
import com.mobiversal.emanuelcristutiu.movieapp.movies_list.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchFragment : Fragment(), SearchMovieInteractionListener {

    private val movieRepository = MoviesRepository.instance
    private var adapter: MoviesAdapter? = null
    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorsRepository.instance
    private var selectedGenres: List<Genre> = emptyList()
    private var selectedActors: List<FavoriteActor> = emptyList()
    private var hasActors = false
    private var hasGenres = false
    private var searchQuery : String? = null

    private fun getMovies() {
        GlobalScope.launch(Dispatchers.IO) {
            getSelectedActors()
            getSelectedGenres()
        }
    }
    private fun getSelectedActors() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedActors = actorRepository.getAll()
            hasActors = true
            checkRequestAndSend()
        }
    }
    private fun getSelectedGenres() {
        GlobalScope.launch(Dispatchers.IO) {
            selectedGenres = genreRepository.getAll()
            hasGenres = true
            checkRequestAndSend()
        }
    }
    private fun checkRequestAndSend() {
        if (hasActors && hasGenres) {
            GlobalScope.launch(Dispatchers.IO) {
                val movies = movieRepository.getAllRemote(
                    convertActorListToString(selectedActors),
                    convertGenreListToString(selectedGenres)
                )
                withContext(Dispatchers.Main) {
                    onRemoteMoviesReady(movies)
                }
            }
        }
    }
    private fun convertGenreListToString(genreList: List<Genre>): String {
        val selectedGenresIds: MutableList<Int> = mutableListOf()
        for (genre in genreList) {
            selectedGenresIds.add(genre.id)
        }
        return selectedGenresIds.joinToString("|")
    }
    private fun convertActorListToString(actorList: List<FavoriteActor>): String {
        val selectedActorIds: MutableList<Int> = mutableListOf()
        for (actor in actorList) {
            selectedActorIds.add(actor.id)
        }
        return selectedActorIds.joinToString("|")
    }

    fun search(query: String) {
        Log.d(TAG, "Search query is: $query")
        GlobalScope.launch(Dispatchers.IO) {
            val movies = movieRepository.searchMovies(query)
            withContext(Dispatchers.Main) {
                onRemoteMoviesReady(movies)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        if(searchQuery.isNullOrBlank()) {
            getMovies()
        }else search(searchQuery!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    private fun onRemoteMoviesReady(movies: List<Movie>) {
        // Get all movies locally
        GlobalScope.launch {
            val favoriteMovies = movieRepository.getAll()
            val newMoviesList = movies.map { movie ->
                val isFavorite = favoriteMovies.find {it.id == movie.id && (it.isFavorite ?: false) } != null
                val isWatched = favoriteMovies.find { it.id == movie.id && (it.isWatched ?: false) } != null

                movie.copy(
                    isFavorite = isFavorite,
                    isWatched = isWatched
                )
            }
            withContext(Dispatchers.Main) {
                setupRecyclerView(newMoviesList)
            }
        }
    }

    private fun setupRecyclerView(movies: List<Movie>) {
        iv_movie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        this.adapter = MoviesAdapter(movies, this)
        iv_movie.adapter = adapter
    }

    override fun updateMovie(movie: Movie) {
        GlobalScope.launch {
            movieRepository.save(movie)
            withContext(Dispatchers.Main) {
                adapter?.notifyDataSetChanged()
            }
        }
    }
}