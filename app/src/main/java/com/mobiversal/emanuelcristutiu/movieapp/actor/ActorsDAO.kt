package com.mobiversal.emanuelcristutiu.movieapp.actor

import androidx.room.*

@Dao
interface ActorsDAO {

    @Query("SELECT * FROM favorite_actors")
    fun getAll(): List<FavoriteActor>

    @Insert
    fun save(favoriteActor: FavoriteActor)

    @Insert
    fun saveAll(favoriteActors: List<FavoriteActor>)

    @Delete
    fun delete(favoriteActor: FavoriteActor)

    @Query("DELETE FROM favorite_actors")
    fun deleteAll()

    @Delete
    fun deleteAll(favoriteActors: List<FavoriteActor>)

    @Transaction
    fun replaceAll(favoriteActors: List<FavoriteActor>) {
        deleteAll()
        saveAll(favoriteActors)
    }

}