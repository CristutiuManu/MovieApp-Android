package com.mobiversal.emanuelcristutiu.movieapp.genres_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiversal.emanuelcristutiu.movieapp.PreferencesActivity
import com.mobiversal.emanuelcristutiu.movieapp.R
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreRepository
import com.mobiversal.emanuelcristutiu.movieapp.movies_list.HamburgerMenuActivity
import kotlinx.android.synthetic.main.activity_genres.*
import kotlinx.android.synthetic.main.activity_genres.saveButton
import kotlinx.android.synthetic.main.activity_preferences.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GenresActivity : AppCompatActivity() {

    private val genreRepository = GenreRepository.instance
    private var genres: List<Genre> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genres)
        getGenres()
        saveButton.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                genreRepository.deleteAll()
                genreRepository.saveAll(getSelectedGenres())
            }
            startActivity(Intent(this, PreferencesActivity::class.java))
        }
    }

    companion object {
        private val TAG = GenresActivity::class.java.simpleName
    }

    private fun getGenres(): List<Genre> {
        GlobalScope.launch(Dispatchers.IO) {
            genres = genreRepository.getAllRemote()
            genres.let { genres ->
                withContext(Dispatchers.Main) {
                    Log.d(GenresActivity.TAG, genres.firstOrNull()?.name ?: "not found")
                    insertPreselectedItems()
                    setupRecyclerView()
                }
            }
        }
        return genres
    }

    private fun setupRecyclerView() {
        val adapter = GenresAdapter(genres)
        val llm = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_genres.layoutManager = llm
        rv_genres.adapter = adapter
    }

    private fun getSelectedGenres(): List<Genre> {
        return genres.filter { genre -> genre.isSelected }
    }

    private fun insertPreselectedItems () {
        GlobalScope.launch(Dispatchers.IO) {
            val savedGenre: List<Genre> = genreRepository.getAll()
            genres.forEach { genre -> genre.isSelected = savedGenre.contains(genre) }
        }
    }

}