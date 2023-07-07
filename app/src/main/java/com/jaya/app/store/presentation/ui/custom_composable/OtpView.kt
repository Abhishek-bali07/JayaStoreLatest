package com.jaya.app.store.presentation.ui.custom_composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jaya.app.store.presentation.theme.appTextStyles

const val PIN_VIEW_TYPE_UNDERLINE = 0
const val PIN_VIEW_TYPE_BORDER = 1

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PinView(
    pinText: String,
    onPinTextChange: (String) -> Unit,
    digitColor: Color = Color.Black ,
    digitSize: TextUnit = 24.sp,
    containerSize: Dp = digitSize.value.dp * 3,
    digitCount: Int = 4,
    type: Int = PIN_VIEW_TYPE_BORDER,
) {
    BasicTextField( modifier = Modifier.padding(horizontal = 5.dp), value = pinText,
        onValueChange = onPinTextChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        cursorBrush = SolidColor(Color.Black),
        textStyle = MaterialTheme.appTextStyles.otpInputStyle,
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(digitCount) { index ->
                    DigitView(index, pinText, digitColor, digitSize, containerSize, type = type)
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        })
}


@Composable
private fun DigitView(
    index: Int,
    pinText: String,
    digitColor: Color,
    digitSize: TextUnit,
    containerSize: Dp,
    type: Int = PIN_VIEW_TYPE_UNDERLINE,
) {
    val modifier = if (type == PIN_VIEW_TYPE_BORDER) {
        Modifier
            .width(containerSize)
            .height(containerSize)
            .border(
                width = 2.dp,
                color = Color(0XFFC7C7C7),

            )
            .padding(10.dp)
    } else Modifier.width(containerSize)

    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (index >= pinText.length) "" else pinText[index].toString(),
            style = MaterialTheme.appTextStyles.otpInputStyle,
            color = digitColor,
            fontSize = digitSize,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
        if (type == PIN_VIEW_TYPE_UNDERLINE) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
                modifier = Modifier
                    .background(digitColor)
                    .height(1.dp)
                    .width(containerSize)
            )
        }
    }
}



