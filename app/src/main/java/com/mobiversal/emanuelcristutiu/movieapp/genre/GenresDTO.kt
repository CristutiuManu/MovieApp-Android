package com.mobiversal.emanuelcristutiu.movieapp.genre

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenresDTO(
    @Json(name= "genres")
    val genres: List<GenreDTO>
) {
    override fun toString(): String {
        return "GenresDTO(genres=$genres)"
    }
}