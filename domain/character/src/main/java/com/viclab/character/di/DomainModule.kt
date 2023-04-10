package com.viclab.character.di

import com.viclab.character.repository.CharacterRepository
import com.viclab.character.usecase.GetCharacterListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideCharacterUseCase(repository: CharacterRepository): GetCharacterListUseCase {
        return GetCharacterListUseCase(repository)
    }
}