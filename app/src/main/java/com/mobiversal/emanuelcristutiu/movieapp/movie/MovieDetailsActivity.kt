package com.mobiversal.emanuelcristutiu.movieapp.movie

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.mobiversal.emanuelcristutiu.movieapp.Constants.KEY_MOVIE_ID
import com.mobiversal.emanuelcristutiu.movieapp.Constants.POSTER_URL
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.movie.video.Video
import com.mobiversal.emanuelcristutiu.movieapp.utils.ImageLoader
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.nav_header_main.imageView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailsActivity: AppCompatActivity() {

    companion object {
        private val TAG = MovieDetailsActivity::class.java.simpleName
    }

    private val moviesRepository: MoviesRepository = MoviesRepository.instance
    private var movie: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        intent?.extras?.let { bundle ->
            val movieId = bundle.getInt(KEY_MOVIE_ID, -1)

            if(movieId == -1) error("Invalid movie id")

            Log.d(TAG, "Movie id is:  $movieId")
            fetchMovieDetails(movieId)
        }
    }

    private fun fetchMovieDetails(movieId: Int) {

        GlobalScope.launch {
            val movie: Movie = moviesRepository.getMovieDetails(movieId)
            this@MovieDetailsActivity.movie = movie

            withContext(Dispatchers.Main) {
                onRemoteMovieDetailsReady(movie)
            }
        }
    }

    private fun onRemoteMovieDetailsReady(movie: Movie) {
        Log.d(TAG, "Movie = $movie")

        populatePoster(movie)
        populateMoviesDetails(movie)
        setYoutubeLink(movie)
        handleButtons()
        fetchLocalMovie(movie)
    }

    private fun fetchLocalMovie(movie: Movie) {
        GlobalScope.launch {
            val savedMovie: Movie? = moviesRepository.getAll().find { it.id == movie.id }


            withContext(Dispatchers.Main) {
                savedMovie?.let { updateButtonsText(it) }
                savedMovie?.let { this@MovieDetailsActivity.movie = it }
            }
        }
    }

    private fun populatePoster (movie: Movie) {

        val hasPoster: Boolean = movie.poster_path?.isNotBlank() ?: false

        val imageUrl = POSTER_URL + movie.poster_path

        if (hasPoster) {
            imageView.isVisible = true
            ImageLoader.loadImage(imageUrl, imageView, this)
        } else {
            imageView.isVisible = false
        }
    }

    private fun populateMoviesDetails(movie: Movie) {
        tw_title.text = movie.title
        tw_release_date.text = movie.release_date
        tw_genres.text = movie.genres.map { it.name }.joinToString ( ", " )
        tw_description.text = movie.overview
    }

    private fun setYoutubeLink( movie: Movie) {
        lifecycle.addObserver(youtube_player)

        val video: Video? = movie.videos.find { it.site == "YouTube" }
        video?.let {
            youtube_player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youtubePlayer: YouTubePlayer){
                youtubePlayer.loadVideo(video.key, 0f)
                }
            })
        }
    }

    private fun handleButtons () {
        button_add_favorite.setOnClickListener {
            this.movie?.let { movie ->
                movie.isFavorite = !(movie.isFavorite ?: false)
                updateMovie(movie)
            }
        }
        button_add_watched.setOnClickListener {
            this.movie?.let { movie ->
                movie.isWatched = !(movie.isWatched ?: false)
                updateMovie(movie)
            }
            }
    }

    private fun updateButtonsText(movie: Movie) {
        button_add_favorite.text = if (movie.isFavorite == true) "REMOVE FAVORITE" else "ADD FAVORITE"
        button_add_watched.text = if (movie.isWatched == true) "REMOVE WATCHED" else "ADD WATCHED"
    }

    private fun updateMovie(movie: Movie) {
        GlobalScope.launch {
            moviesRepository.save(movie)

            withContext(Dispatchers.Main) {
                updateButtonsText(movie)
            }

        }
    }


}