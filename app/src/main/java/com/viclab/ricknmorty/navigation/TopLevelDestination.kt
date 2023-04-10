package com.viclab.ricknmorty.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.ui.graphics.vector.ImageVector
import com.viclab.ricknmorty.R

enum class TopLevelDestination(
    val titleTextId: Int,
    val actionIcon: ImageVector? = null,
) {
    CHARACTERS(R.string.characters, Icons.Default.FilterList),
}