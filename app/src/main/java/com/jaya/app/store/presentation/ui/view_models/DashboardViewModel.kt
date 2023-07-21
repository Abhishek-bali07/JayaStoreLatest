package com.jaya.app.store.presentation.ui.view_models

import android.text.method.TextKeyListener.clear
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.entities.Product
import com.jaya.app.store.core.usecase.DashboardUseCase
import com.jaya.app.store.core.utils.helper.AppNavigator
import com.jaya.app.store.presentation.states.DrawerMenuItem
import com.jaya.app.store.presentation.states.DrawerMenus
import com.jaya.app.store.presentation.states.castListToRequiredTypes
import com.jaya.app.store.presentation.states.castValueToRequiredTypes
import com.jaya.app.store.utils.helper_impl.SavableMutableState
import com.jaya.app.store.utils.helper_impl.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    private val dashboardUseCase: DashboardUseCase,
    savedStateHandle: SavedStateHandle,
): ViewModel() {



    val products = mutableStateListOf<Product>()
    val searchProduct = mutableStateListOf<Product>()


    val scaffoldState = ScaffoldState(
        drawerState = DrawerState(initialValue = DrawerValue.Closed),
        snackbarHostState = SnackbarHostState()
    )


    val drawerGuestureState = SavableMutableState(
        key = UiData.DrawerGuestureState,
        savedStateHandle = savedStateHandle,
        initialData = false
    )



    private val _drawerMenus = MutableStateFlow(DrawerMenus.values().map {
        DrawerMenuItem(menu = it)
    })
    val drawerMenus = _drawerMenus.asStateFlow()


    val totalItem = mutableStateOf("")
    val totalIssued = mutableStateOf("")
    val isshowSearch = mutableStateOf(false)

    val searchTxt = mutableStateOf<String>("")

    var quotationsLoading by mutableStateOf(false)
        private set

    init {
        getStockData()
    }


    fun getStockData() {
        dashboardUseCase.StockData().onEach {
            when (it.type) {
                EmitType.Loading ->{
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            quotationsLoading = it
                        }
                    }

                }
                EmitType.IssueItem ->{
                    it.value?.castValueToRequiredTypes<String>()?.let {
                        totalIssued.value = it
                    }
                }
                EmitType.TotalItem -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {
                        totalItem.value = it
                    }
                }
                EmitType.StockItem -> {
                    it.value?.castListToRequiredTypes<Product>()?.let {
                        searchProduct.clear()
                        searchProduct.addAll(it)
                        products.clear()
                        products.addAll(it)
                    }
                }


                else -> {}
            }
        }.launchIn(viewModelScope)
    }


    fun addProduct(){
     appNavigator.tryNavigateTo(
         Destination.AddProductScreen(),
         popUpToRoute = null,
         inclusive = false,
         isSingleTop = true
     )
    }


    fun issueProduct(){
        appNavigator.tryNavigateTo(
            Destination.IssueProductScreen(),
            popUpToRoute = null,
            inclusive = false,
            isSingleTop = true
        )
    }

    fun onChangeSearchTxt(search :String){
        searchTxt.value = search
        viewModelScope.launch {
            delay(400L)
            searchProduct.filter {
                if (it.productTitle != null){
                    return@filter it.productTitle.lowercase().contains(search.lowercase())
                }
                false
            }.let {
                products.clear()
                products.addAll(it)
            }
        }
    }




    fun onDrawerMenuClicked(index: Int, selected: DrawerMenuItem) {
        _drawerMenus.update {
            it.map { item ->
                if (item == selected) {
                    item.copy(selected = true)
                } else item.copy(selected = false)
            }
        }
        navigateToMenu(selected.menu)
    }




    private fun navigateToMenu(menu: DrawerMenus) {
       /* when (menu) {


            DrawerMenus.Logout -> {
                useCases.logout().onEach {
                    when (it.type) {
                        EntryType.NAVIGATE -> {
                            it.data?.castValueToRequiredTypes<Navigation>()?.apply {
                                navigator.popAndNavigate(
                                    destination = destination.toDestination(),
                                    singleTop = singleTop,
                                    inclusive = popUpto
                                )
                            }
                        }

                        else -> {}
                    }
                }.launchIn(viewModelScope)
            }
        }*/
    }

}