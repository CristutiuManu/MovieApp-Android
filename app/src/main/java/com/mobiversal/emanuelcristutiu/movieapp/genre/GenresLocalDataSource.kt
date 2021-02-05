package com.mobiversal.emanuelcristutiu.movieapp.genre


import com.mobiversal.emanuelcristutiu.movieapp.database.Database

class GenresLocalDataSource(
    database: Database
) {


    private val genreDao: GenreDAO = database.movieAppDatabase.genresDAO()

    fun getAll() = genreDao.getAll()
    fun save(genre: Genre) = genreDao.save(genre)
    fun saveAll(genres: List<Genre>) = genreDao.saveAll(genres)
    fun delete(genre: Genre) = genreDao.delete(genre)
    fun deleteAll() = genreDao.deleteAll()
    fun deleteAll(genres: List<Genre>) = genreDao.deleteAll(genres)
    fun replaceAll(genres: List<Genre>) = genreDao.replaceAll(genres)

}