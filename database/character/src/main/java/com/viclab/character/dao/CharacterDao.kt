package com.viclab.character.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.viclab.character.model.CharacterEntity

@Dao
interface CharacterDao {

    @Query("DELETE FROM character")
    suspend fun clearCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Transaction
    suspend fun insertAll(repositoryList: List<CharacterEntity>)

    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getById(id: Long): CharacterEntity?

    @Query(
        value = """
            SELECT * FROM character 
    """
    )
    fun getCharacterList(): PagingSource<Int, CharacterEntity>

}