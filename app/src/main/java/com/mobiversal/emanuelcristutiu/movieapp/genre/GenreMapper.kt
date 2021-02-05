package com.mobiversal.emanuelcristutiu.movieapp.genre

class GenreMapper {

    fun map(dto: GenreDTO):Genre {
        return Genre(
            id = dto.id,
            name = dto.name

        )
    }

}