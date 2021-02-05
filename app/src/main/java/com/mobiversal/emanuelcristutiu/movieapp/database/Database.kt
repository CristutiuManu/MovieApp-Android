package com.mobiversal.emanuelcristutiu.movieapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mobiversal.emanuelcristutiu.movieapp.actor.ActorsDAO
import com.mobiversal.emanuelcristutiu.movieapp.actor.FavoriteActor
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreDAO
import com.mobiversal.emanuelcristutiu.movieapp.movie.Movie
import com.mobiversal.emanuelcristutiu.movieapp.movie.MovieDAO

class Database private constructor(){

    companion object {
        val instance = Database()
    }

    @androidx.room.Database(
        entities = [FavoriteActor::class, Genre::class, Movie::class],
        version = 5
    )
    abstract class MovieAppDatabase : RoomDatabase() {

        abstract fun actorsDAO() : ActorsDAO

        abstract fun genresDAO() : GenreDAO

        abstract fun moviesDAO() : MovieDAO
    }

    lateinit var movieAppDatabase: MovieAppDatabase
        private set


    fun initialize(context: Context) {

        this.movieAppDatabase = Room.databaseBuilder(
            context,
            MovieAppDatabase:: class.java,
            "movie_app.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

}

