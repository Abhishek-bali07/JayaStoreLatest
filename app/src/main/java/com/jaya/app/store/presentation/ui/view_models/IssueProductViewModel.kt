package com.jaya.app.store.presentation.ui.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssueProductViewModel @Inject constructor(

):ViewModel(){



    val isExpandedItem = mutableStateOf(false)

    val isExpandedVendor = mutableStateOf(false)
}