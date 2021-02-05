package com.mobiversal.emanuelcristutiu.movieapp.movie

import com.mobiversal.emanuelcristutiu.movieapp.database.Database

class MovieLocalDataSource (database: Database){

    private val moviesDao: MovieDAO = database.movieAppDatabase.moviesDAO()
    fun getAll() = moviesDao.getAll()
    fun save(favoriteMovie: Movie) = moviesDao.save(favoriteMovie)
    fun saveAll(favoriteMovie: List<Movie>) = moviesDao.saveAll(favoriteMovie)
    fun delete(favoriteMovie: Movie) = moviesDao.delete(favoriteMovie)
    fun deleteAll() = moviesDao.deleteAll()
    fun deleteAll(favoriteMovie: List<Movie>) = moviesDao.deleteAll(favoriteMovie)
    fun replaceAll(favoriteMovie: List<Movie>) = moviesDao.replaceAll(favoriteMovie)
    fun getFavoriteMovies() = moviesDao.getFavoriteMovies()
    fun getWatchedMovies() = moviesDao.getWatchedMovies()
}