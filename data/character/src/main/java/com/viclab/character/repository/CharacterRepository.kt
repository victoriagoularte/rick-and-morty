package com.viclab.character.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.viclab.model.character.Character

interface CharacterRepository {
    fun characters(name: String, status: String?) : Flow<PagingData<Character>>
}