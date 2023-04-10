package com.viclab.character

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.viclab.character.converter.EpisodeConverter
import com.viclab.character.dao.RemoteKeysDao
import com.viclab.character.dao.CharacterDao
import com.viclab.character.dao.LocationDao
import com.viclab.character.dao.OriginDao
import com.viclab.character.model.RemoteKeysEntity
import com.viclab.character.model.CharacterEntity
import com.viclab.character.model.LocationEntity
import com.viclab.character.model.OriginEntity

@Database(
    entities = [CharacterEntity::class, RemoteKeysEntity::class, OriginEntity::class, LocationEntity::class],
    version = 1
)
@TypeConverters(EpisodeConverter::class)
abstract class RickNMortyDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun originDao(): OriginDao
    abstract fun locationDao(): LocationDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}