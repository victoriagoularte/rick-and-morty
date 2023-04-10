package com.viclab.character.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val originId: Long,
    val locationId: Long,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
    var page: Int = 0,
)
