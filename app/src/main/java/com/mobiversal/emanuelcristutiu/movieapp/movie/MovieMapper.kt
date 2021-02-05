package com.mobiversal.emanuelcristutiu.movieapp.movie

import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreMapper
import com.mobiversal.emanuelcristutiu.movieapp.movie.video.VideoMapper

class MovieMapper {

    private val genresMapper = GenreMapper()
    private val videoMapper = VideoMapper()

    fun map(dto: MovieDTO): Movie {
        return Movie(
            id= dto.id,
            title = dto.title,
            poster_path = dto.poster_path,
            release_date = dto.release_date,
            overview = dto.overview,
            isFavorite = dto.isFavorite,
            isWatched = dto.isWatched
        ).apply {
            this.genres = dto.genres?.map { genresMapper.map(it) } ?: emptyList()
            this.videos = dto.videosDto?.results?.map { videoMapper.map(it) } ?: emptyList()
        }
    }
}