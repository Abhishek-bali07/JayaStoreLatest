package com.jaya.app.store.presentation.ui.screens.intro

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.Text
import com.jaya.app.store.presentation.states.statusBarColor
import com.jaya.app.store.presentation.ui.view_models.SplashViewModel

@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel = hiltViewModel()
){
    val animation =
        updateTransition(targetState = splashViewModel.splashAnimation.value, label = "").animateDp(
            label = ""
        ) {
            when (it) {
                true -> 0.dp
                false -> 200.dp
            }
        }

    val fontAnimation =
        updateTransition(
            targetState = splashViewModel.splashAnimation.value,
            label = ""
        ).animateFloat(label = "", transitionSpec = {
            tween(500)
        }) {
            when (it) {
                true -> 0f
                false -> 1f
            }
        }

    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xff4BB26D))
                .statusBarColor(color = Color(0xff4BB26D)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Image(
                painter = painterResource(id = R.drawable.jayalogo),
                contentDescription = null,
                 modifier = Modifier
                    .size(animation.value),
            )
            R.string.app_name.Text(
                style = MaterialTheme.typography.h3.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                ),
                modifier = Modifier.graphicsLayer {
                    scaleX = fontAnimation.value
                    scaleY = fontAnimation.value
                }
            )

        }



        Column( modifier = Modifier
            .wrapContentHeight()
            .padding(vertical = 12.dp)
            .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally)
        {

            splashViewModel.connectivityStatus.collectAsState().apply {
                when (value) {
                    true -> {
                        CircularProgressIndicator(
                            color = Color.White,
                            strokeWidth = 1.dp,
                            modifier = Modifier.size(48.dp)
                        )
                        R.string.app_name.Text(style = MaterialTheme.typography.body1)
                    }

                    false -> {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null,
                            tint = Color.Red,
                            modifier = Modifier.size(48.dp)
                        )
                        R.string.no_internet.Text(style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    }




}


