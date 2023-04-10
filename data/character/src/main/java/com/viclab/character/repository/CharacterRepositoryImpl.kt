package com.viclab.character.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.viclab.character.RickNMortyDatabase
import com.viclab.character.datasource.local.PageCharacterRemoteMediator
import com.viclab.character.mapper.asCharacter
import com.viclab.model.character.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val ITEMS_PER_PAGE = 20

@OptIn(ExperimentalPagingApi::class)
class CharacterRepositoryImpl @Inject constructor(
    private val database: RickNMortyDatabase,
    val remoteMediator: PageCharacterRemoteMediator
) : CharacterRepository {

    override fun characters(name: String, status: String?): Flow<PagingData<Character>> = Pager(
        config = PagingConfig(
            pageSize = ITEMS_PER_PAGE,
            prefetchDistance = 10,
            initialLoadSize = ITEMS_PER_PAGE,
        ),
        pagingSourceFactory = {
            database.characterDao().getCharacterList(name, status)
        },
        remoteMediator = remoteMediator
    ).flow.map { pagingData ->
        pagingData.map {
            val origin = database.originDao().getById(it.originId)
            val location = database.locationDao().getById(it.locationId)
            it.asCharacter(origin, location)
        }
    }
}
