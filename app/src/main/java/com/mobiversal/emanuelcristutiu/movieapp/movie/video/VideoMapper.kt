package com.mobiversal.emanuelcristutiu.movieapp.movie.video

class VideoMapper {

    fun map(videoDTO: VideoDTO): Video {
        return Video(
            key = videoDTO.key,
            site = videoDTO.site
        )
    }
}