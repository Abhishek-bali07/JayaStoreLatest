package com.jaya.app.store.presentation.ui.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.entities.ItemProduct
import com.jaya.app.store.core.entities.Plant
import com.jaya.app.store.core.entities.Section
import com.jaya.app.store.core.entities.SectionData
import com.jaya.app.store.core.entities.SubmitData
import com.jaya.app.store.core.usecase.IssueProductUseCase
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
class IssueProductViewModel @Inject constructor(
    val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle,
    private val productIssueUseCase: IssueProductUseCase
):ViewModel()
{

    val selectedProduct = mutableStateOf<ItemProduct?>(null)

    var pageLoading by mutableStateOf(false)
        private set


    val selectedItem = mutableStateOf<SectionData?>(null)

    val selectPlant = mutableStateOf<Plant?>(null)

    val selectSection = mutableStateOf<Section?>(null)



    val isExpandedItem = mutableStateOf(false)

    val isExpandedPlant = mutableStateOf(false)

    val isExpandedSection = mutableStateOf(false)

    val isExpandedSubsection = mutableStateOf(false)


    val itemNumber = mutableStateOf("")

    val orderBy = mutableStateOf("")

    val takingName = mutableStateOf("")

    val where = mutableStateOf("")

    val searchTxt = mutableStateOf<String>("")
    val plantSearchTxt = mutableStateOf<String>("")

    /*private val _productDetails = MutableStateFlow<List<ItemProduct>>(emptyList())
    val productDetails =  _productDetails.asStateFlow()*/

    private val _sectionDetails = MutableStateFlow<List<Section>>(emptyList())
    val sectionDetails = _sectionDetails.asStateFlow()


   /* private val _plantDetails = MutableStateFlow<List<Plant>>(emptyList())
    val plantDetails = _plantDetails.asStateFlow()*/


    private val _subsectionDetails = MutableStateFlow<List<SectionData>>(emptyList())
    val subsectionDetails = _subsectionDetails.asStateFlow()


    val products = mutableStateListOf<ItemProduct>()

    val searchProduct = mutableStateListOf<ItemProduct>()


    val plants = mutableStateListOf<Plant>()

    val searchPlant = mutableStateListOf<Plant>()



    fun onChangeOrder(co:String) {
        orderBy.value = co
    }
    fun onChangeItemNumber(cin:String) {
        itemNumber.value = cin
    }

    fun onChangeTakingName(tname:String) {
        takingName.value = tname
    }

    fun onChangeAskedName(aname:String) {
        where.value = aname
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

        initialSubSectionData()
        initialProductData()
        initialPlantData()
        initialSectionData()
        validateInputs()
    }



    private  fun validateInputs() {
        viewModelScope.launch {
            while (true) {
                delay(200L)
                enableBtn.setValue(
                    when{
                        itemNumber.value.isEmpty() ->{
                            false
                        }
                       orderBy.value.isEmpty() ->{
                            false
                        }
                        takingName.value.isEmpty() ->{
                            false
                        }
                        where.value.isEmpty() ->{
                            false
                        }
                        else -> true
                    }
                )
            }
        }
    }


    fun initialProductData() {
       productIssueUseCase.Products().onEach {
            when (it.type) {
                EmitType.Loading ->{
                    it.value?.apply {
                        castValueToRequiredTypes<Boolean>()?.let {
                            pageLoading = it
                        }
                    }
                }

                EmitType.productDetails -> {
                    it.value?.castListToRequiredTypes<ItemProduct>()?.let { data->
                        searchProduct.clear()
                        searchProduct.addAll(data)
                        products.clear()
                        products.addAll(data)


                   /*     _productDetails.update { emptyList() }
                        _productDetails.update { data }*/

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


    fun onChangeSearchTxt(search :String){
        searchTxt.value = search
        viewModelScope.launch {
            delay(400L)
            searchProduct.filter {
                if (it.productName != null){
                    return@filter it.productName.lowercase().contains(search.lowercase())
                }
                false
            }.let {
                products.clear()
                products.addAll(it)

            }

        }
    }


    fun onChangePlantTxt(psearch :String){
        plantSearchTxt.value = psearch
        viewModelScope.launch {
            delay(400L)
            searchPlant.filter {
                if (it.plantName != null){
                    return@filter it.plantName.lowercase().contains(psearch.lowercase())
                }
                false
            }.let {
                plants.clear()
                plants.addAll(it)

            }

        }
    }


    private  fun initialSectionData() {
       productIssueUseCase.Section().onEach {
            when (it.type) {
                EmitType.sectionDetails-> {
                    it.value?.castListToRequiredTypes<Section>()?.let { data->
                        _sectionDetails.update { data }

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


    private fun initialPlantData(){
        productIssueUseCase.Plants().onEach {
            when (it.type) {
                EmitType.plantDetails-> {
                    it.value?.castListToRequiredTypes<Plant>()?.let { data->
                        searchPlant.clear()
                        searchPlant.addAll(data)
                        plants.clear()
                        plants.addAll(data)

                       /* _plantDetails.update { data }*/

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


    private  fun initialSubSectionData() {
        productIssueUseCase.Subsection().onEach {
            when (it.type) {
                EmitType.subsectionDetails-> {
                    it.value?.castListToRequiredTypes<SectionData>()?.let { data->
                        _subsectionDetails.update { data }

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



    fun SubmitIssue(){
        val submitData = SubmitData(
            productName =selectedProduct.value?.productId ?: "" ,
            pQuantity = itemNumber.value,
            orderBy = orderBy.value,
            takenName =takingName.value,
            aboutProduct =where.value,
            selectPlant=selectPlant.value?.plantId ?: "",
            selectSection =selectSection.value?.sectionId ?: "",
            subSection = selectedItem.value?.subsectionId ?: "",
        )
        productIssueUseCase.submitProduct(submitData).onEach {
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