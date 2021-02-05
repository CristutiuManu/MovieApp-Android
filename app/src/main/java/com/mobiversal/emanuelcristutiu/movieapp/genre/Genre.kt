package com.mobiversal.emanuelcristutiu.movieapp.genre

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "id")
    @NotNull
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false

) {

    override fun equals(other: Any?) = (other is Genre) && id==other.id

    override fun toString(): String {
        return "Genre(id=$id, name='$name')"
    }
}