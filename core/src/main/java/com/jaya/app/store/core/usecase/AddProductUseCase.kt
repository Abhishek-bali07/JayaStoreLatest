package com.jaya.app.store.core.usecase

import android.util.Log
import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.domain.repositories.AddProductRepository
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddProductUseCase @Inject constructor(
    private  val repository: AddProductRepository,
    private  val appStore: AppStore
){






    fun SupplierDetails() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getSupplier(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.supplierDetails, value = supplier))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }

            }

            else -> {}
        }
    }



    fun GstDetails() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getGst(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.gstDetails, value = gst))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }

            }

            else -> {}
        }
    }


    fun addProductData(uploadData: UploadData) = flow {
        Log.d("TESTING", "addProductData: $uploadData")
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.addProductData(appStore.userId(), uploadData)){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status && isAdded){
                        true ->{
                            emit(Data(type = EmitType.Inform, message))
                            emit(Data(type = EmitType.Navigate, Destination.DashBoardScreen))
                        }

                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            else -> {}
        }
    }


}


/*   fun getVendorData() = flow<Data> {
       emit(Data(EmitType.Loading, value = true))
       when(val response = repository.getVendorItem(appStore.userId())){
           is Resource.Success ->{
               emit(Data(EmitType.Loading, false))
               response.data?.apply {
                   when(status){
                       true -> {
                           emit(Data(type = EmitType.VendorData, value = vendor))
                       }
                       else -> {
                           emit(Data(EmitType.BackendError, message))
                       }
                   }
               }
           }

           else -> {}
       }
   }*/



/*   fun getStockData() = flow<Data> {
       emit(Data(EmitType.Loading, value = true))
       when(val response = repository.getStockItem(appStore.userId())){
           is Resource.Success ->{
               emit(Data(EmitType.Loading, false))
               response.data?.apply {
                   when(status){
                       true -> {
                           emit(Data(type = EmitType.stockData, value = stockData))
                       }
                       else -> {
                           emit(Data(EmitType.BackendError, message))
                       }
                   }
               }
           }

           else -> {}
       }
   }*/