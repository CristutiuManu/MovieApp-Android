package com.mobiversal.emanuelcristutiu.movieapp.genre

import com.mobiversal.emanuelcristutiu.movieapp.Constants.API_KEY
import com.mobiversal.emanuelcristutiu.movieapp.Constants.LANGUAGE
import com.mobiversal.emanuelcristutiu.movieapp.network.executeAndDeliver
import retrofit2.Retrofit

class GenreRemoteDataSource (retrofit: Retrofit) {

    private val apiService: GenreAPIService = retrofit.create(GenreAPIService::class.java)
    private val genreMapper = GenreMapper()

    @Throws(Exception::class)
    fun getGenres(): List<Genre> {
        return apiService.getGenres(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .genres
            .map {genreMapper.map(it)}
    }





}