package com.jaya.app.store.presentation.ui.view_models


import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.app.store.core.usecase.AddProductUseCase
import com.jaya.app.store.core.utils.helper.AppNavigator
import com.jaya.app.store.utils.helper_impl.SavableMutableState
import com.jaya.app.store.utils.helper_impl.UiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductViewModel  @Inject constructor(
    val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
    private  val addProductUseCase: AddProductUseCase
) :ViewModel(){

    val vendorSelectedText = mutableStateOf("")

    val itemSelectedText = mutableStateOf("")


    val productVendor = listOf("vendor1", "vendor2", "vendor3", "vendor4")

    val selectedItem = listOf("vendor1", "vendor2", "vendor3", "vendor4")


    val isSourceExpanded = mutableStateOf(false)

    val isItemExpanded = mutableStateOf(false)


    val productRate = mutableStateOf("")
    val stockItem = mutableStateOf("")


    fun onChangeRate(rate:String) {
        productRate.value = rate
    }

    fun onChangeStck(stock:String) {
        stockItem.value = stock
    }


    val enableBtn = SavableMutableState(
        key = UiData.AddNowBtnEnable,
        savedStateHandle = savedStateHandle,
        initialData = false
    )

    val addLoading = SavableMutableState(
        key = UiData.NewClientApiLoading,
        savedStateHandle = savedStateHandle,
        initialData = false
    )

    init {
        validateInputs()
    }


    private  fun validateInputs(){
        viewModelScope.launch {
            while (true) {
                delay(200L)
                enableBtn.setValue(
                    when{
                        productRate.value.isEmpty() ->{
                            false
                        }
                        stockItem.value.isEmpty() ->{
                            false
                        }



                        else -> true
                    }
                )
            }
        }
    }


    private  fun getVendor(){
      addProductUseCase.getVendorData()

    }

    private  fun getStocks(){
        addProductUseCase.getStockData()
    }
}