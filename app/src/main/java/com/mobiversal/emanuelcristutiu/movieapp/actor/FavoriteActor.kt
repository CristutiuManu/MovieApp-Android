package com.mobiversal.emanuelcristutiu.movieapp.actor

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_actors")
data class FavoriteActor(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl: String?,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false
) {

    override fun equals(other: Any?) = (other is FavoriteActor) && id==other.id

    override fun toString(): String {
        return "FavoriteActor(id=$id, name='$name', imgUrl='$imgUrl', isSelected=$isSelected)"
    }


}
