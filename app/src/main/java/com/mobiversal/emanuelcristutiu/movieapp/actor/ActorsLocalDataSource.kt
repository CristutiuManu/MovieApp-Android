package com.mobiversal.emanuelcristutiu.movieapp.actor

import com.mobiversal.emanuelcristutiu.movieapp.database.Database

class ActorsLocalDataSource(
    database: Database
) {

    private val actorsDao: ActorsDAO = database.movieAppDatabase.actorsDAO()

    fun getAll() = actorsDao.getAll()
    fun save(favoriteActor: FavoriteActor) = actorsDao.save(favoriteActor)
    fun saveAll(favoriteActors: List<FavoriteActor>) = actorsDao.saveAll(favoriteActors)
    fun delete(favoriteActor: FavoriteActor) = actorsDao.delete(favoriteActor)
    fun deleteAll() = actorsDao.deleteAll()
    fun deleteAll(favoriteActors: List<FavoriteActor>) = actorsDao.deleteAll(favoriteActors)
    fun replaceAll(favoriteActors: List<FavoriteActor>) = actorsDao.replaceAll(favoriteActors)


}