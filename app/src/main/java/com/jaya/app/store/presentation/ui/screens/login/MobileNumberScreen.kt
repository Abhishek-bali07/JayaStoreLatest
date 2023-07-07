package com.jaya.app.store.presentation.ui.screens.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaya.app.store.presentation.theme.appTextStyles
import com.jaya.app.store.presentation.ui.custom_composable.AppButton
import com.jaya.app.store.presentation.ui.view_models.MobileViewModel
import com.jaya.app.store.R
import com.jaya.app.store.presentation.states.resourceString
import com.jaya.app.store.presentation.ui.custom_composable.MobileNumberInputField

@Composable
fun  MobileNumberScreen(
    mobileViewModel: MobileViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        MobileScreenCoverSection()

        if (mobileViewModel.saveData.value){
            (mobileViewModel)

        }else{
            MobileNumberInputSection(mobileViewModel)
        }

    }
}

@Composable
private fun ColumnScope.MobileNumberInputSection(mobileViewModel: MobileViewModel) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .weight(1.2f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {


        MobileNumberInputField(
            onNumberChange = mobileViewModel::onNumberChange,
            modifier = Modifier
                .fillMaxWidth(fraction = .9f)
                .padding(vertical = 10.dp),
            textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                backgroundColor = Color.Black.copy(alpha = .2f)
            )
        )
        AppButton(
            enable = mobileViewModel.enableBtn.value,
            loading = mobileViewModel.loginLoading.value,
            action = mobileViewModel::otpSend,
            name = R.string.get_otp
        )



    }
}

@Composable
fun ColumnScope.MobileScreenCoverSection() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id =R.drawable.jayalogo),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(fraction = .8f)
                .padding(vertical = 20.dp)
        )
        Text(
            text = R.string.welcome.resourceString(),
            style = MaterialTheme.appTextStyles.getStartedStyle
        )
        Text(
            text = R.string.loginhere.resourceString(),
            style = MaterialTheme.appTextStyles.getStartedStyle
        )
        Column( modifier = Modifier.fillMaxWidth(fraction = .9f),
            horizontalAlignment = Alignment.Start) {


        }
    }
}
