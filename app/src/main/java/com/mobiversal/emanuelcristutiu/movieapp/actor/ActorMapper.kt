package com.mobiversal.emanuelcristutiu.movieapp.actor

class ActorMapper {

    fun map(dto: ActorDTO): FavoriteActor {
        return FavoriteActor(
            id = dto.id,
            name = dto.name,
            imgUrl = dto.imgUrl

        )
    }
}