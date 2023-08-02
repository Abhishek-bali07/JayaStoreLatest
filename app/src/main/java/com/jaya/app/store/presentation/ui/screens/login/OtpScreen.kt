package com.jaya.app.store.presentation.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.presentation.ui.view_models.MobileViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.resourceString
import com.jaya.app.store.presentation.theme.appTextStyles
import com.jaya.app.store.presentation.ui.custom_composable.AppButton
import com.jaya.app.store.presentation.ui.custom_composable.PinView

@Composable
fun OtpScreen(
    mobileViewModel: MobileViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ImageSection()

        OtpInputSection(mobileViewModel)
    }
}

@Composable
fun ColumnScope.ImageSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(.8f),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Image(
            painter = painterResource(id =R.drawable.jayalogo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(fraction = .7f)
                .padding(vertical = 20.dp)
        )
        androidx.compose.material.Text(
            text = R.string.welcome.resourceString(),
            style = MaterialTheme.appTextStyles.getStartedStyle
        )

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ColumnScope.OtpInputSection(mobileViewModel: MobileViewModel) {

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
      //  verticalArrangement = Arrangement.Start
    ) {
        Text(
            text = R.string.verifyOtp.resourceString(),
            style = MaterialTheme.appTextStyles.getStartedStyle,
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(fraction = .9f).padding(vertical = 5.dp),
            maxLines = 1,
            shape = RoundedCornerShape(12.dp),
            textStyle = MaterialTheme.appTextStyles.mobileNumberStyle,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            value = mobileViewModel.mobileNumber.value,
            onValueChange = {
                mobileViewModel.onNumberChange(it)
            }


        )

        PinView(
            pinText = mobileViewModel.otp.value,
            onPinTextChange = {
                mobileViewModel.onOtp(it)
                if (mobileViewModel.otp.value.length == 4) {
                    focusManager.clearFocus()
                    keyboardController?.hide()

                }
            }
        )



        AppButton(
            enable = mobileViewModel.venableBtn.value,
            loading = mobileViewModel.loading.value,
            action = { mobileViewModel.appLogin() },
            name = R.string.verify
        )

        TextButton(onClick = mobileViewModel::resendOtp) {
            Text(
                text = R.string.resendOtp.resourceString(),
                style = MaterialTheme.appTextStyles.resendCodeStyle
            )
        }
    }

}


