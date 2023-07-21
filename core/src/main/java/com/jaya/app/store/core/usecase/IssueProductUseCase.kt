package com.jaya.app.store.core.usecase

import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.domain.repositories.IssueProductRepository
import com.jaya.app.store.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IssueProductUseCase @Inject constructor(
    private  val repository: IssueProductRepository,
    private  val appStore: AppStore
) {
    fun Products() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getProduct(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.productDetails, value =product))
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


    fun Subsection() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getSubsection(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.subsectionDetails, value = sectionData))
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


    fun Plants() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getPlant(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.plantDetails, value = plant))
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



    fun Section() = flow {
        emit(Data(EmitType.Loading, value = true))
        when(val response = repository.getSection(appStore.userId())){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(EmitType.sectionDetails, value = section))
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