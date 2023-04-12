package com.viclab.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioFilterChips(
    tags: List<String>,
    selectedFilter: String?,
    modifier: Modifier,
    onFilterSelected: (String) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(tags) {tag ->
            val isSelected = selectedFilter == tag
            FilterChip(
                label = { Text(text = tag) },
                selected = isSelected, 
                onClick = { onFilterSelected(tag) }
            )
        }
    }
}

@Preview
@Composable
fun FilterTagsPreview() {
    val tags = listOf("Dead", "Alive", "Unknown")
    var selectedFilter by remember { mutableStateOf<String?>(null) }
    RadioFilterChips(tags, selectedFilter, Modifier) { tag ->
        selectedFilter = tag
    }
}