package com.mobiversal.emanuelcristutiu.movieapp.movie.video

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class VideoDTO(
    val key: String,
    val site: String
){
    override fun toString(): String {
        return "VideoDTO(key='$key', site='$site')"
    }
}