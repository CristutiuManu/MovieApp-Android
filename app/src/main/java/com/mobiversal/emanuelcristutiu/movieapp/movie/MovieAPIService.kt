package com.mobiversal.emanuelcristutiu.movieapp.movie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPIService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("language")language: String,
        @Query("with_cast")withCast: String,
        @Query("with_genres")withGenres: String
    ): Call<MoviesDTO>

    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("query") query: String
    ): Call<MoviesDTO>

    @GET("movie/{movieId}")
    fun getMovieDetails(
        @Path("movieId") movieId : Int,
        @Query("api_key") api_key: String,
        @Query("append_to_response") appendToResponse: String
    ): Call<MovieDTO>

}