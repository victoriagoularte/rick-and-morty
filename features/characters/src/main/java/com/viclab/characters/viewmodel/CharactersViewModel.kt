package com.viclab.characters.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.viclab.character.usecase.GetCharacterListUseCase
import com.viclab.model.character.Character
import com.viclab.core.coroutines.di.DispatchersIo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val useCase: GetCharacterListUseCase,
    @DispatchersIo private val dispatcher: CoroutineDispatcher
): ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<Character>>(PagingData.empty())
    var uiState: StateFlow<PagingData<Character>> = _uiState.asStateFlow()

    fun characters(name: String? = null, status: String? = null) {
        uiState = useCase(name.orEmpty(), status)
            .flowOn(dispatcher)
            .stateIn(viewModelScope, SharingStarted.Eagerly, PagingData.empty())
    }

    fun detail(characterId: Long) {

    }

}