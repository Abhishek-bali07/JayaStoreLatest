package com.jaya.app.store.presentation.ui.view_models


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.entities.Gst
import com.jaya.app.store.core.entities.StockData
import com.jaya.app.store.core.entities.Supplier
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





    val selectedSupplier = mutableStateOf<Supplier?>(null)

    val selectedGst = mutableStateOf<Gst?>(null)


    val isSourceExpanded = mutableStateOf(false)

    val isItemExpanded = mutableStateOf(false)


    val brandName = mutableStateOf("")
    val productName = mutableStateOf("")
    val basic = mutableStateOf("")
    val rate = mutableStateOf("")
    val quantity = mutableStateOf("")
    val state = mutableStateOf("")


   /* private val _vendorDetails = MutableStateFlow<List<Vendor>>(emptyList())
    val vendorDetails = _vendorDetails.asStateFlow()

    private val _stockDetails = MutableStateFlow<List<StockData>>(emptyList())
    val stockDetails = _stockDetails.asStateFlow()*/


    private val _supplierDetails = MutableStateFlow<List<Supplier>>(emptyList())
    val supplierDetails =  _supplierDetails.asStateFlow()


    private val _gstDetails = MutableStateFlow<List<Gst>>(emptyList())
    val gstDetails =  _gstDetails.asStateFlow()

    fun onChangeBrand(rate:String) {
        brandName.value = rate
    }

    fun onChangeProduct(stock:String) {
        productName.value = stock
    }

    fun onChangeBasic(b:String) {
        basic.value = b
    }


    fun onChangeQuantity(cq:String) {
        quantity.value = cq
    }


    fun onChangeState(cs:String) {
        state.value = cs
    }


    fun onChangeRate(r:String) {
        rate.value = r
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
        initialSupplierData()
        initialGstData()
        validateInputs()
    }


    private  fun validateInputs(){
        viewModelScope.launch {
            while (true) {
                delay(200L)
                enableBtn.setValue(
                    when{
                        brandName.value.isEmpty() ->{
                            false
                        }
                        productName.value.isEmpty() ->{
                            false
                        }
                        basic.value.isEmpty() ->{
                            false
                        }
                        rate.value.isEmpty() ->{
                            false
                        }
                        quantity.value.isEmpty() ->{
                            false
                        }
                        state.value.isEmpty() ->{
                            false
                        }

                        else -> true
                    }
                )
            }
        }
    }




    private  fun initialSupplierData() {
       addProductUseCase.SupplierDetails() .onEach {
            when (it.type) {
                EmitType.supplierDetails -> {
                    it.value?.castListToRequiredTypes<Supplier>()?.let { data->
                        _supplierDetails.update { data }

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


    private  fun initialGstData() {
        addProductUseCase.GstDetails().onEach {
            when (it.type) {
                EmitType.gstDetails-> {
                    it.value?.castListToRequiredTypes<Gst>()?.let { data->
                       _gstDetails.update { data }

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
            productName = productName.value,
            brandName = brandName.value,
            selectedSupplier = selectedSupplier.value?.supplierId ?: "",
            basic =basic.value,
            selectGst = selectedGst.value?.gstId ?: "",
            rate =rate.value,
            quantity = quantity.value,
            state = state.value
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