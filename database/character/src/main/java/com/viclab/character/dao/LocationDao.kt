package com.viclab.character.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.viclab.character.model.LocationEntity

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationEntity): Long

    @Query("SELECT * FROM location WHERE id = :id")
    suspend fun getById(id: Long): LocationEntity

    @Query("DELETE FROM location")
    suspend fun clearLocations()
}