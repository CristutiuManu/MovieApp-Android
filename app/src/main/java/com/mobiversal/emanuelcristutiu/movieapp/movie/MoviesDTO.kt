package com.mobiversal.emanuelcristutiu.movieapp.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class MoviesDTO(
    @Json(name = "results")
    val movies: List<MovieDTO>
)
{
    override fun toString(): String {
        return "MoviesDTO(movies=$movies)"
    }
}