package com.viclab.character.usecase

import com.viclab.character.repository.CharacterRepository
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(private val repository: CharacterRepository) {

    operator fun invoke(name: String, status: String?) = repository.characters(name, status)
}