package com.viclab.character.di

import com.viclab.character.RickNMortyDatabase
import com.viclab.character.dao.RemoteKeysDao
import com.viclab.character.dao.CharacterDao
import com.viclab.character.dao.LocationDao
import com.viclab.character.dao.OriginDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun providesCharacterDao(
        database: RickNMortyDatabase,
    ): CharacterDao = database.characterDao()

    @Provides
    fun providesOriginDao(
        database: RickNMortyDatabase,
    ): OriginDao = database.originDao()

    @Provides
    fun providesLocationDao(
        database: RickNMortyDatabase,
    ): LocationDao = database.locationDao()

    @Provides
    fun providesRemoteKeysDao(
        database: RickNMortyDatabase,
    ): RemoteKeysDao = database.remoteKeysDao()
}