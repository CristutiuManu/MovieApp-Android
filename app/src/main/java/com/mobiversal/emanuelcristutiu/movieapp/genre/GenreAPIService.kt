package com.mobiversal.emanuelcristutiu.movieapp.genre

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreAPIService {

    @GET("genre/movie/list")
    fun getGenres(
        @Query("api" + "_key") apiKey: String,
        @Query("language")language: String
    ): Call<GenresDTO>
}