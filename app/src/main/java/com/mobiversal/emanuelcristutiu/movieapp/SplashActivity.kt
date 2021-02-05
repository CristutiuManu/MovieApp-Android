package com.mobiversal.emanuelcristutiu.movieapp


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Bundle
import com.mobiversal.emanuelcristutiu.movieapp.actor.ActorsRepository
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreRepository
import com.mobiversal.emanuelcristutiu.movieapp.movies_list.HamburgerMenuActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private val actorRepository = ActorsRepository.instance
    private var selectedGenres: List<Genre> = emptyList()
    private var selectedActors: List<FavoriteActor> = emptyList()
    private var hasActors = false
    private var hasGenres = false

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
                if (selectedActors.isNotEmpty() || selectedGenres.isNotEmpty()) {
                    startActivity(Intent(this@SplashActivity, HamburgerMenuActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, PreferencesActivity::class.java))
                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        scheduleSplashScreen()
    }
    private fun scheduleSplashScreen() {
        val splashScreenDuration = 800L
        Handler().postDelayed(
            {
                routeToAppropriatePage()
                finish()
            },
            splashScreenDuration
        )
    }
    private fun routeToAppropriatePage() {
        getSelectedActors()
        getSelectedGenres()
    }

}