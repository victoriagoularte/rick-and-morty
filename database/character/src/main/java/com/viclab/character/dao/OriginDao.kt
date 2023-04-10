package com.viclab.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viclab.character.model.OriginEntity

@Dao
interface OriginDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: OriginEntity): Long

    @Query("SELECT * FROM origin WHERE id = :id")
    suspend fun getById(id: Long): OriginEntity

    @Query("DELETE FROM origin")
    suspend fun clearOrigins()
}