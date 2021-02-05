package com.mobiversal.emanuelcristutiu.movieapp.actor

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ActorsDTO(
    @Json(name = "results")
    val actors: List<ActorDTO>
) {
    override fun toString(): String {
        return "ActorsDTO(actors=$actors)"
    }
}