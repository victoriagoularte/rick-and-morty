package com.viclab.characters.viewmodel

import androidx.paging.PagingData
import com.viclab.core.viewmodel.UIState
import com.viclab.model.character.Character

data class CharactersState(
    val characters: PagingData<Character> = PagingData.empty()
) : UIState {

    fun setCharacters(characters: PagingData<Character>) = copy(characters = characters)
}
