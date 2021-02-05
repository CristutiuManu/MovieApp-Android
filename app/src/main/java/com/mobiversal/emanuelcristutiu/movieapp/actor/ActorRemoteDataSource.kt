package com.mobiversal.emanuelcristutiu.movieapp.actor

import com.mobiversal.emanuelcristutiu.movieapp.Constants.API_KEY
import com.mobiversal.emanuelcristutiu.movieapp.Constants.LANGUAGE
import com.mobiversal.emanuelcristutiu.movieapp.network.executeAndDeliver
import retrofit2.Retrofit

class ActorRemoteDataSource(retrofit: Retrofit) {
    private val apiService: ActorsAPIService = retrofit.create(ActorsAPIService::class.java)
    private val actorsMapper = ActorMapper()

    fun getActors(): List<FavoriteActor> {
        return apiService.getActors(API_KEY, LANGUAGE)
            .executeAndDeliver()
            .actors
            .map { actorsMapper.map(it) }
    }

    fun searchActors(query :String): List<FavoriteActor> {
        return apiService.searchActors(API_KEY, LANGUAGE,query)
            .executeAndDeliver()
            .actors
            .map { actorsMapper.map(it) }


    }
}