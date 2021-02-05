package com.mobiversal.emanuelcristutiu.movieapp.genre

import androidx.room.*

@Dao
interface GenreDAO {


    @Query("SELECT * FROM genres")
    fun getAll(): List<Genre>

    @Insert
    fun save(genre: Genre)

    @Insert
    fun saveAll(genres: List<Genre>)

    @Delete
    fun delete(genre: Genre)

    @Query("DELETE FROM genres")
    fun deleteAll()

    @Delete
    fun deleteAll(genres: List<Genre>)


    @Transaction
    fun replaceAll(genres: List<Genre>) {
        deleteAll()
        saveAll(genres)
    }
    
}