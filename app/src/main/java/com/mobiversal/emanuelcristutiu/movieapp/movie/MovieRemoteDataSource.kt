package com.mobiversal.emanuelcristutiu.movieapp.movie

import com.mobiversal.emanuelcristutiu.movieapp.Constants
import com.mobiversal.emanuelcristutiu.movieapp.Constants.API_KEY
import com.mobiversal.emanuelcristutiu.movieapp.Constants.LANGUAGE
import com.mobiversal.emanuelcristutiu.movieapp.network.executeAndDeliver
import retrofit2.Retrofit

class MovieRemoteDataSource(retrofit: Retrofit) {
    private val apiService: MovieAPIService = retrofit.create(MovieAPIService::class.java)
    private val moviesMapper = MovieMapper()

    fun getMovies(withCast: String, withGenres: String): List<Movie> {
        return apiService.getMovies(API_KEY, LANGUAGE, withCast, withGenres)
            .executeAndDeliver()
            .movies
            .map { moviesMapper.map(it) }
    }

    @Throws(Exception::class)
    fun searchMovies(query: String): List<Movie> {
        return apiService.searchMovies(Constants.API_KEY, Constants.LANGUAGE, query)
            .executeAndDeliver()
            .movies
            .map { moviesMapper.map((it)) }
    }

    fun getMovieDetails(movieId: Int): Movie {
        val dto = apiService.getMovieDetails(
            movieId,
            Constants.API_KEY,
            "videos"
        ).executeAndDeliver()

            return moviesMapper.map(dto)
    }

}