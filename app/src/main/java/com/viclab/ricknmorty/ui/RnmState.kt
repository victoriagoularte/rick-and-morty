package com.viclab.ricknmorty.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.viclab.characters.navigation.charactersRoute
import com.viclab.characters.navigation.navigateToCharacters
import com.viclab.ricknmorty.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberRnmAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): RnmAppState {
    return remember(navController, coroutineScope) {
        RnmAppState(navController, coroutineScope)
    }
}

@Stable
class RnmAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            charactersRoute -> TopLevelDestination.CHARACTERS
            else -> null
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.CHARACTERS -> navController.navigateToCharacters(topLevelNavOptions)
        }
    }


    fun onBackClick() {
        navController.popBackStack()
    }
}
