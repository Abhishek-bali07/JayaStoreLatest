package com.jaya.app.store.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jaya.app.store.core.common.constants.Destination

import com.jaya.app.store.navigation.screen_transition.AppScreenTransitions

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    route: String? = null,
    enterTransition: AppScreenTransitions.ScreenEnterTransition,
    popEnterTransition: AppScreenTransitions.ScreenPopEnterTransition,
    exitTransition: AppScreenTransitions.ScreenExitTransition,
    popExitTransition: AppScreenTransitions.ScreenPopExitTransition,
    builder: NavGraphBuilder.() -> Unit,
){
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder,
    )
}


fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        deepLinks = deepLinks,
        arguments = arguments,
        route = destination.fullRoute,
        content = content,
    )
}