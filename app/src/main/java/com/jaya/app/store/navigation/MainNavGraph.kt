package com.jaya.app.store.navigation

import android.window.SplashScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.utils.helper.NavigationIntent
import com.jaya.app.store.navigation.screen_transition.AppScreenTransitions
import com.jaya.app.store.presentation.ui.screens.intro.SplashScreen
import com.jaya.app.store.presentation.ui.view_models.BaseViewModel
import kotlinx.coroutines.channels.Channel

@Composable
fun MainNavGraph(
    navHostController: NavHostController,
    navigationChannel: Channel<NavigationIntent>,
    paddingValues: PaddingValues,
    baseViewModel: BaseViewModel

){
    navHostController.NavEffects(navigationChannel)
    AppNavHost(
            navController = navHostController,
            startDestination = Destination.SplashScreen,
            modifier = Modifier.padding(paddingValues),
            enterTransition = AppScreenTransitions.ScreenEnterTransition,
            popEnterTransition = AppScreenTransitions.ScreenPopEnterTransition,
            exitTransition = AppScreenTransitions.ScreenExitTransition,
            popExitTransition = AppScreenTransitions.ScreenPopExitTransition,
        ){

        composable(destination = Destination.SplashScreen){
            SplashScreen()
        }


    }
}