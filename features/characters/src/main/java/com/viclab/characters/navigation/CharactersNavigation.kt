package com.viclab.characters.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.viclab.characters.CharactersRoute
import com.viclab.characters.model.FilterStatus

const val charactersRoute = "characters_route"

fun NavController.navigateToCharacters(navOptions: NavOptions? = null) {
    this.navigate(charactersRoute, navOptions)
}

fun NavGraphBuilder.charactersGraph() {
    composable(route = charactersRoute) {
        CharactersRoute()
    }
}
