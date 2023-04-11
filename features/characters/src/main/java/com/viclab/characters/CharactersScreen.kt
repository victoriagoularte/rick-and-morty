package com.viclab.characters

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.viclab.characters.model.FilterStatus
import com.viclab.characters.viewmodel.CharactersViewModel
import com.viclab.features.characters.R
import com.viclab.model.character.Character
import com.viclab.ui.component.AvatarCard
import com.viclab.ui.component.AvatarCardData
import com.viclab.ui.component.RnmTopAppBar

@Composable
internal fun CharactersRoute(
    viewModel: CharactersViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {

    viewModel.characters()
    val charactersState = viewModel.uiState.collectAsLazyPagingItems()

    CharactersScreen(
        charactersState,
        onFiltered = { name, status -> viewModel.reload(name, status?.name.orEmpty()) },
        modifier = modifier
    )
}

@Composable
internal fun CharactersScreen(
    characterState: LazyPagingItems<Character>,
    onFiltered: (String, FilterStatus?) -> Unit,
    modifier: Modifier = Modifier
) {
    var shouldShowFilter by rememberSaveable { mutableStateOf(false) }

    if(shouldShowFilter) {
        FilterCharacterScreen(
            modifier,
            onFiltered,
            onBackPressed = { shouldShowFilter = false },
            onFilterClicked = { shouldShowFilter = false }
        )
    } else {
        CharacterList(
            modifier,
            characterState,
            onFilterClick = { shouldShowFilter = true }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CharacterList(
    modifier: Modifier,
    characterState: LazyPagingItems<Character>,
    onFilterClick: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        RnmTopAppBar(
            titleRes = R.string.characters,
            actionIcon = Icons.Default.FilterList,
            actionIconContentDescription = "Filter list",
            onActionClick = onFilterClick
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(PaddingValues(horizontal = 16.dp, vertical = 8.dp)),
        ) {
            itemsIndexed(characterState) { index, character ->
                character?.let {
                    AvatarCard(
                        AvatarCardData(
                            it.name,
                            it.image
                        )
                    )
                }
            }

            when (characterState.loadState.refresh) {
                is LoadState.Error -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_message),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }

                }
                is LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(8.dp),
                                text = stringResource(R.string.loading)
                            )

                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                else -> {}
            }

            when (val state = characterState.loadState.append) { // Pagination
                is LoadState.Error -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_message),
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
                is LoadState.Loading -> { // Pagination Loading UI
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.error_message),
                            )
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}

