package com.viclab.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.viclab.characters.model.FilterStatus
import com.viclab.features.characters.R
import com.viclab.ui.component.RnmTopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCharacterScreen(
    modifier: Modifier,
    onFiltered: (String, FilterStatus?) -> Unit,
    onBackPressed: () -> Unit,
    onFilterClicked: () -> Unit,
) {
    var textFieldState by remember { mutableStateOf(TextFieldValue()) }
    var tagSelected by remember { mutableStateOf<FilterStatus?>(null) }

    Column(modifier = modifier
        .fillMaxSize()
    ) {
        RnmTopAppBar(
            titleRes = R.string.filter,
            navigationIcon = Icons.Default.ChevronLeft,
            navigationIconContentDescription = "",
            onNavigationClick = { onBackPressed() }
        )
        TextField(
            value = textFieldState,
            onValueChange = { textFieldState = it },
            placeholder = { Text(stringResource(R.string.name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        val tags = FilterStatus.values().asList()
        LazyRow(
            contentPadding = PaddingValues(horizontal = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(tags) {tag ->
                TagItem(tag = tag, onSelected = { tagSelected = tag })
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                onFiltered(textFieldState.text, tagSelected)
                onFilterClicked()
            }
        ) {
            Text(text = stringResource(R.string.to_filter))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagItem(tag: FilterStatus, onSelected: (FilterStatus) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = { onSelected(tag) }
    ) {
        Text(
            text = tag.name,
            modifier = Modifier.padding(8.dp),
            color = MaterialTheme.colorScheme.surface
        )
    }
}

@Composable
fun FilterCharacterScreenPreview() {
    FilterCharacterScreen(modifier = Modifier, onFiltered = { _, _ ->  }, onBackPressed = { /*TODO*/ }, onFilterClicked = {})
}