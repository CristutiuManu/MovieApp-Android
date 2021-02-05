package com.mobiversal.emanuelcristutiu.movieapp.movie

import com.mobiversal.emanuelcristutiu.movieapp.database.Database
import com.mobiversal.emanuelcristutiu.movieapp.network.APIClient
import java.lang.Exception

class MoviesRepository  private constructor(){

    companion object {
        val instance = MoviesRepository()
    }

    private val movieLocalDataSource = MovieLocalDataSource(
        Database.instance
    )
    private val movieRemoteDataSource = MovieRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    fun getAll() = movieLocalDataSource.getAll()
    fun save(favoriteMovie: Movie) = movieLocalDataSource.save(favoriteMovie)
    fun saveAll(favoriteMovie: List<Movie>) = movieLocalDataSource.saveAll(favoriteMovie)
    fun delete(favoriteMovie: Movie) = movieLocalDataSource.delete(favoriteMovie)
    fun deleteAll() = movieLocalDataSource.deleteAll()
    fun deleteAll(favoriteMovie: List<Movie>) = movieLocalDataSource.deleteAll(favoriteMovie)
    fun replaceAll(favoriteMovie: List<Movie>) = movieLocalDataSource.replaceAll(favoriteMovie)
    fun getFavoriteMovies() = movieLocalDataSource.getFavoriteMovies()
    fun getWatchedMovies() = movieLocalDataSource.getWatchedMovies()
    fun getMovieDetails(movieId: Int) = movieRemoteDataSource.getMovieDetails(movieId)
    @Throws(Exception::class)
    suspend fun getAllRemote(withCast: String, withGenres: String) = movieRemoteDataSource.getMovies(withCast, withGenres)

    @Throws(Exception::class)
    suspend fun searchMovies(query: String) = movieRemoteDataSource.searchMovies(query)

}