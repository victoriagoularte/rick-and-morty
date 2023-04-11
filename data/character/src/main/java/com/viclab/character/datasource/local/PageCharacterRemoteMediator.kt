package com.viclab.character.datasource.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.viclab.character.datasource.remote.CharacterRemoteDataSource
import com.viclab.character.RickNMortyDatabase
import com.viclab.character.mapper.asEntity
import com.viclab.character.model.CharacterEntity
import com.viclab.character.model.RemoteKeysEntity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val ONE_PAGE = 1
private const val ONE_KEY = 1
private const val SOURCE_DURATION = 1L

@OptIn(ExperimentalPagingApi::class)
class PageCharacterRemoteMediator @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val database: RickNMortyDatabase,
): RemoteMediator<Int, CharacterEntity>() {

    var name = ""
    var status = ""

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(SOURCE_DURATION, TimeUnit.MINUTES)

        return if (System.currentTimeMillis() - (database.remoteKeysDao().getCreationTime() ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, CharacterEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { repository ->
            database.remoteKeysDao().getRemoteKeyByCharacterId(repository.id)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, CharacterEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { repository ->
            database.remoteKeysDao().getRemoteKeyByCharacterId(repository.id)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, CharacterEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.remoteKeysDao().getRemoteKeyByCharacterId(id)
            }
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val page: Int = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(ONE_KEY) ?: ONE_KEY
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                val prevKey = remoteKeys?.prevKey
                prevKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        }

        try {
            val apiResponse = remoteDataSource.characters(name, status, page)
//            delay(1000L)
            val characters = apiResponse.characterList
            val endOfPaginationReached = characters.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.characterDao().clearCharacters()
                }
                val prevKey = if (page > ONE_PAGE) page - ONE_PAGE else null
                val nextKey = if (endOfPaginationReached) null else page + ONE_PAGE
                val remoteKeys = characters.map {
                    RemoteKeysEntity(characterId = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }

                database.remoteKeysDao().insertAll(remoteKeys)
                val characterEntities = characters.map {
                    val locationId = database.locationDao().insert(it.location.asEntity())
                    val originId = database.originDao().insert(it.origin.asEntity())
                    it.asEntity(originId, locationId)
                }

                database.characterDao().insertAll(
                    characterEntities.onEachIndexed { _, character -> character.page = page })
            }

            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        }
    }
}