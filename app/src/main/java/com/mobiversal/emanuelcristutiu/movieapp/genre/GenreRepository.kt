package com.mobiversal.emanuelcristutiu.movieapp.genre

import com.mobiversal.emanuelcristutiu.movieapp.database.Database
import com.mobiversal.emanuelcristutiu.movieapp.network.APIClient

class GenreRepository private constructor(){

    companion object{

        val instance = GenreRepository()
    }

    private val genreLocalDataSource = GenresLocalDataSource(Database.instance)


    private val genreRemoteDataSource = GenreRemoteDataSource(
        retrofit = APIClient.instance.retrofit
    )

    suspend fun getAll() = genreLocalDataSource.getAll()
    suspend fun save(genre: Genre) = genreLocalDataSource.save(genre)
    suspend fun saveAll(genres: List<Genre>) = genreLocalDataSource.saveAll(genres)
    suspend fun delete(genre: Genre) = genreLocalDataSource.delete(genre)
    suspend fun deleteAll() = genreLocalDataSource.deleteAll()
    suspend fun deleteAll(genres: List<Genre>) = genreLocalDataSource.deleteAll(genres)
    suspend fun replaceAll(genres: List<Genre>) = genreLocalDataSource.replaceAll(genres)
    @Throws(Exception::class)
    suspend fun getAllRemote() = genreRemoteDataSource.getGenres()

}
