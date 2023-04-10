package com.viclab.character.datasource.remote

import com.viclab.character.service.CharacterService
import javax.inject.Inject

class CharacterRemoteDataSourceImpl @Inject constructor(
    private val service: CharacterService
) : CharacterRemoteDataSource {

    override suspend fun characters(page: Int) = service.characters(page)
}