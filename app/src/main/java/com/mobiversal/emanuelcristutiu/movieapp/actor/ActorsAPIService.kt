package com.mobiversal.emanuelcristutiu.movieapp.actor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ActorsAPIService {

    @GET("person/popular")
    fun getActors(
        @Query("api_key") apiKey: String,
        @Query("language")language: String
    ): Call<ActorsDTO>

    @GET("search/person")
    fun searchActors(
        @Query("api_key") apiKey: String,
        @Query("language")language: String,
        @Query("query")query : String
    ):Call<ActorsDTO>

}