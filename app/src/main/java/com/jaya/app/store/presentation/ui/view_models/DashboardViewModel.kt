package com.jaya.app.store.presentation.ui.view_models

import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.jaya.app.store.utils.helper_impl.SavableMutableState
import com.jaya.app.store.utils.helper_impl.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {


    val scaffoldState = ScaffoldState(
        drawerState = DrawerState(initialValue = DrawerValue.Closed),
        snackbarHostState = SnackbarHostState()
    )


    val drawerGuestureState = SavableMutableState(
        key = UiData.DrawerGuestureState,
        savedStateHandle = savedStateHandle,
        initialData = false
    )


    val totalItem = mutableStateOf<Int?>(null)
    val totalIssued = mutableStateOf<Int?>(null)
}