package com.mobiversal.emanuelcristutiu.movieapp.movie

import androidx.room.Ignore
import com.mobiversal.emanuelcristutiu.movieapp.genre.Genre
import com.mobiversal.emanuelcristutiu.movieapp.genre.GenreDTO
import com.mobiversal.emanuelcristutiu.movieapp.movie.video.VideosDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDTO(
    val id: Int,
    val title: String,
    @Json(name = "poster_path")
    val poster_path: String?,
    val release_date: String?,
    val overview: String,
    var isFavorite: Boolean?,
    var isWatched: Boolean?,
    var genres: List<GenreDTO>?,
    @Json(name="videos")
    var videosDto: VideosDTO?

) {
    override fun toString(): String {
        return "MovieDTO(id=$id, title='$title', poster_path=$poster_path, release_date='$release_date', overview='$overview', isFavorite=$isFavorite, isWatched=$isWatched, genres=$genres, videosDto=$videosDto)"
    }
}

