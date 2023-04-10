package com.viclab.character.service

import com.viclab.character.model.CharacterListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterService {

    @GET("/api/character")
    suspend fun characters(@Query("page") page: Int = 0): CharacterListResponse
}