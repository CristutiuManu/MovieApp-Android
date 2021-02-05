package com.mobiversal.emanuelcristutiu.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.mobiversal.emanuelcristutiu.movieapp.actors_list.ActorsActivity
import com.mobiversal.emanuelcristutiu.movieapp.genres_list.GenresActivity
import com.mobiversal.emanuelcristutiu.movieapp.movies_list.HamburgerMenuActivity
import kotlinx.android.synthetic.main.activity_preferences.*

class PreferencesActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        val actorsButton: Button = findViewById(R.id.actors_button)
        val genresButton : Button = findViewById(R.id.genres_button)

        actorsButton.setOnClickListener { startActivity(Intent(this, ActorsActivity::class.java)) }
        genresButton.setOnClickListener { startActivity(Intent(this, GenresActivity::class.java)) }
        saveButton.setOnClickListener { startActivity(Intent(this, HamburgerMenuActivity::class.java))}

    }
}