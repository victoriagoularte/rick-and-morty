package com.viclab.character.datasource.remote

import com.viclab.character.model.CharacterListResponse

interface CharacterRemoteDataSource {

    suspend fun characters(name: String?, status: String?, page: Int) : CharacterListResponse
}