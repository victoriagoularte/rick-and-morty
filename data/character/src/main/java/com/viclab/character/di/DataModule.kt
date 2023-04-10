package com.viclab.character.di

import com.viclab.character.datasource.remote.CharacterRemoteDataSource
import com.viclab.character.datasource.remote.CharacterRemoteDataSourceImpl
import com.viclab.character.repository.CharacterRepository
import com.viclab.character.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindCharacterRemoteDataSource(
        characterRemoteDataSource: CharacterRemoteDataSourceImpl
    ): CharacterRemoteDataSource

    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository
}