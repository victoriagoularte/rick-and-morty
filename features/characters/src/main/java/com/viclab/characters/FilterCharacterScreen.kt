package com.viclab.characters

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.viclab.characters.model.FilterStatus
import com.viclab.features.characters.R
import com.viclab.ui.component.RadioFilterChips
import com.viclab.ui.component.RnmTopAppBar
import com.viclab.ui.theme.RickNMortyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterCharacterScreen(
    modifier: Modifier,
    onFiltered: (String, String?) -> Unit,
    onBackPressed: () -> Unit,
    onFilterClicked: () -> Unit,
) {
    var textFieldState by remember { mutableStateOf(TextFieldValue()) }
    var tagSelected by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        RnmTopAppBar(
            titleRes = R.string.filter,
            navigationIcon = Icons.Default.ChevronLeft,
            navigationIconContentDescription = "",
            onNavigationClick = { onBackPressed() }
        )
        Text(
            text = stringResource(id = R.string.name).uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        TextField(
            value = textFieldState,
            onValueChange = { textFieldState = it },
            placeholder = { Text(stringResource(R.string.name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = stringResource(id = R.string.status).uppercase(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        val tags = FilterStatus.values().asList().map { it.name }
        RadioFilterChips(tags, tagSelected, Modifier) { tag ->
            tagSelected = tag
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


@Preview(name = "DarkMode", uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 360, heightDp = 640)
@Preview(widthDp = 360, heightDp = 640)
@Composable
fun FilterCharacterScreenPreview() {
    RickNMortyTheme {
        FilterCharacterScreen(modifier = Modifier, onFiltered = { _, _ ->  }, onBackPressed = { /*TODO*/ }, onFilterClicked = {})
    }
}