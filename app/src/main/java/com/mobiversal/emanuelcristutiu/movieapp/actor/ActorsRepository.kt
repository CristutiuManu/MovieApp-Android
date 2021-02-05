package com.mobiversal.emanuelcristutiu.movieapp.actor

import com.mobiversal.emanuelcristutiu.movieapp.database.Database
import com.mobiversal.emanuelcristutiu.movieapp.network.APIClient

class ActorsRepository private constructor(){

    companion object {

        val instance = ActorsRepository()
    }
    private val actorLocalDataSource = ActorsLocalDataSource(Database.instance)

    private val actorRemoteDataSource = ActorRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    suspend fun getAll() = actorLocalDataSource.getAll()
    suspend fun save(actor: FavoriteActor) = actorLocalDataSource.save(actor)
    suspend fun saveAll(actors: List<FavoriteActor>) = actorLocalDataSource.saveAll(actors)
    suspend fun delete(actor: FavoriteActor) = actorLocalDataSource.delete(actor)
    suspend fun deleteAll() = actorLocalDataSource.deleteAll()
    suspend fun deleteAll(actors: List<FavoriteActor>) = actorLocalDataSource.deleteAll(actors)
    suspend fun replaceAll(actors: List<FavoriteActor>) = actorLocalDataSource.replaceAll(actors)
    @Throws(Exception::class)
    suspend fun getAllRemote() = actorRemoteDataSource.getActors()
    suspend fun searchActorsRemote(query: String) =actorRemoteDataSource.searchActors(query)


}