package com.jaya.app.store.presentation.ui.screens.intro

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.statusBarColor
import com.jaya.app.store.presentation.ui.view_models.SplashViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel()
){


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xff4BB26D))
            .statusBarColor(color = Color(0xff4BB26D)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        SplashImageSection(R.drawable.jayalogo)
    }



}


@Composable
private fun ColumnScope.SplashImageSection(@DrawableRes splash: Int) {
    Box(
        modifier = Modifier.weight(2f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = splash),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(fraction = 0.7f)
        )
    }
}