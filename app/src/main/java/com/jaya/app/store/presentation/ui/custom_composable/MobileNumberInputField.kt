package com.jaya.app.store.presentation.ui.custom_composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jaya.app.store.presentation.states.MobileNumberValidator
import com.jaya.app.store.presentation.theme.appTextStyles

@UiComposable
@Composable
internal fun  MobileNumberInputField(
    onNumberChange: (String) -> Unit,
    modifier: Modifier,
    textFieldColors: TextFieldColors,
) {
    val phoneNumber = rememberSaveable {
        mutableStateOf("")
    }
    val focusMgr = LocalFocusManager.current
    OutlinedTextField(
        value = phoneNumber.value,
        onValueChange = {
            if (it.isEmpty() || it.contains(Regex("^\\d+\$"))) {
                phoneNumber.value = derivedStateOf {
                    it.take(MobileNumberValidator.MOBILE_NUMBER_PATTERN.count {
                        it == MobileNumberValidator.MASK_CHAR
                    })
                }.value
            }
            if (phoneNumber.value.length == 10) focusMgr.clearFocus()
        },
        modifier = modifier,
        maxLines = 1,
        shape = RoundedCornerShape(6.dp),
        textStyle = MaterialTheme.appTextStyles.mobileNumberStyle,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusMgr.clearFocus()
            }
        ),
        visualTransformation = MobileNumberValidator(),
        colors = textFieldColors,
        placeholder = {
            Text(text = MobileNumberValidator.MOBILE_NUMBER_PATTERN,
                style = MaterialTheme.appTextStyles.mobileNumberStyle.copy(color = Color.LightGray))
        }
    )

    LaunchedEffect(key1 = phoneNumber.value) {
        onNumberChange(phoneNumber.value)
    }
}