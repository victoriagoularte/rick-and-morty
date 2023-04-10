package com.viclab.character.model

import com.google.gson.annotations.SerializedName

data class CharacterListResponse(
    @SerializedName("results")
    val characterList: List<CharacterResponse>
)
