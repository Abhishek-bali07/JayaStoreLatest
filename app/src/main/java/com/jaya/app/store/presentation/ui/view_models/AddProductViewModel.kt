package com.jaya.app.store.presentation.ui.view_models


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.entities.StockData
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.entities.Vendor
import com.jaya.app.store.core.usecase.AddProductUseCase
import com.jaya.app.store.core.utils.helper.AppNavigator
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
class AddProductViewModel  @Inject constructor(
    val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
    private  val addProductUseCase: AddProductUseCase
) :ViewModel(){





    val selectedVendor = mutableStateOf<Vendor?>(null)

    val selectedItem = mutableStateOf<StockData?>(null)


    val isSourceExpanded = mutableStateOf(false)

    val isItemExpanded = mutableStateOf(false)


    val productRate = mutableStateOf("")
    val stockItem = mutableStateOf("")

    private val _vendorDetails = MutableStateFlow<List<Vendor>>(emptyList())
    val vendorDetails = _vendorDetails.asStateFlow()

    private val _stockDetails = MutableStateFlow<List<StockData>>(emptyList())
    val stockDetails = _stockDetails.asStateFlow()

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
        initialVendorData()
        initialStockData()
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




    private  fun initialVendorData() {
        addProductUseCase.vendorDetails().onEach {
            when (it.type) {
                EmitType.vendorDetails -> {
                    it.value?.castListToRequiredTypes<Vendor>()?.let { data->
                        _vendorDetails.update { data }

                    }
                }
                EmitType.BackendError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {

                    }
                }

                EmitType.NetworkError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {

                    }
                }

                else -> {}
            }

        }.launchIn(viewModelScope)

    }


    private  fun initialStockData() {
        addProductUseCase.ItemDetails().onEach {
            when (it.type) {
                EmitType.stockDetails-> {
                    it.value?.castListToRequiredTypes<StockData>()?.let { data->
                        _stockDetails.update { data }

                    }
                }
                EmitType.BackendError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {

                    }
                }

                EmitType.NetworkError -> {
                    it.value?.castValueToRequiredTypes<String>()?.let {

                    }
                }

                else -> {}
            }

        }.launchIn(viewModelScope)

    }


    fun uploadProductData(){
        val uploadData = UploadData(
            vendorName = selectedVendor.value?.vendorId ?: "",
            selectedItem = selectedItem.value?.stockId ?: "",
            rate = productRate.value,
            stockNumber = stockItem.value,
        )
        addProductUseCase.addProductData(uploadData).onEach {
            when(it.type){
                EmitType.Loading ->{
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            addLoading.setValue(it)
                        }
                    }
                }

                EmitType.Inform ->{
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {

                        }
                    }
                }

                EmitType.Navigate -> {
                    it.value?.apply {
                        castValueToRequiredTypes<Destination>()?.let { destination ->
                            appNavigator.tryNavigateBack()
                        }
                    }
                }
                EmitType.NetworkError -> {
                    it.value?.apply {
                        castValueToRequiredTypes<String>()?.let {

                        }
                    }
                }
                EmitType.BackendError -> {
                    it.value?.apply {
                        castValueToRequiredTypes<String>()?.let {

                        }
                    }
                }


                else -> {}
            }

        }.launchIn(viewModelScope)
    }
}