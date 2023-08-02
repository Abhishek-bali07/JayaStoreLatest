package com.jaya.app.store.presentation.ui.custom_composable

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.store.presentation.states.bottomToUp
import com.jaya.app.store.presentation.states.resourceString
import com.jaya.app.store.presentation.states.screenHeight
import com.jaya.app.store.presentation.states.screenWidth
import com.jaya.app.store.presentation.states.upToBottom


@Composable
fun AppButton (
    enable: Boolean,
    loading: Boolean,
    action: () -> Unit,
    @StringRes name: Int
){

    AnimatedContent(
        targetState = loading,
        transitionSpec = {
            if(targetState && !initialState) {
                upToBottom()
            } else {
                bottomToUp()
            }
        }
    ) {
        if (!it) {
            Button(
                onClick = action,
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.9f).padding(vertical = 5.dp),
                elevation = ButtonDefaults.elevation(
                    pressedElevation = 10.dp,
                    defaultElevation = 7.dp
                ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                ),
                contentPadding = PaddingValues(
                    top = screenHeight * 0.02f,
                    bottom = screenHeight * 0.02f
                ),
                enabled = enable
            ) {
                Text(
                    text = name.resourceString(),
                    style =   TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    ),
                )
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(width = screenWidth * 0.15f, height = screenHeight * 0.15f)
                    .padding(bottom = screenHeight * 0.05f),
                color = Color.Black,
                strokeWidth = 5.dp,
            )
        }
    }
}