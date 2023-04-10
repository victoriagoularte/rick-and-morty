package com.viclab.character.di

import com.viclab.character.RickNMortyDatabase
import com.viclab.character.datasource.local.PageCharacterRemoteMediator
import com.viclab.character.datasource.remote.CharacterRemoteDataSource
import com.viclab.character.datasource.remote.CharacterRemoteDataSourceImpl
import com.viclab.character.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    fun provideRepositoryService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Provides
    fun provideRemoteMediator(
        remoteDataSource: CharacterRemoteDataSourceImpl,
        database: RickNMortyDatabase
    ) = PageCharacterRemoteMediator(remoteDataSource, database)
}