package com.viclab.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viclab.character.model.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_key WHERE character_id = :id")
    suspend fun getRemoteKeyByCharacterId(id: Long): RemoteKeysEntity?

    @Query("DELETE FROM remote_key")
    suspend fun clearRemoteKeys()

    @Query("SELECT CREATED_AT FROM remote_key ORDER BY CREATED_AT DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}