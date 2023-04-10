package com.viclab.character.model

data class CharacterResponse(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: OriginResponse,
    val location: LocationResponse,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String,
) {
    data class OriginResponse(
        val name: String,
        val url: String,
    )

    data class LocationResponse(
        val name: String,
        val url: String,
    )
}