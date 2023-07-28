package com.jaya.app.store.presentation.ui.view_models


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.jaya.app.store.core.utils.helper.AppNavigator
import com.jaya.app.store.utils.helper_impl.AppConnectivity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class BaseViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    val connectivity: AppConnectivity
) : ViewModel(){

    var refreshLoadDataArg = mutableStateOf(false)


    var productionDataLoadArg = mutableStateOf("")

}