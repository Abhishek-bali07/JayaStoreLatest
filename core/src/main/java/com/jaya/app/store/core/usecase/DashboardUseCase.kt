package com.jaya.app.store.core.usecase

import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.domain.repositories.DashboardRepository
import com.jaya.app.store.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private  val repository: DashboardRepository,
    private  val appStore: AppStore
){
    fun StockData() = flow<Data> {
        emit(Data(EmitType.Loading, value = true))
        when (val response = repository.getStock(appStore.userId(),)) {
            is Resource.Success -> {
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            emit(Data(type = EmitType.TotalItem,  value = this.totalItem))
                            emit(Data(type = EmitType.IssueItem,  value = this.issuedItem))
                            emit(Data(type = EmitType.StockItem, value = this.products))
                        }
                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Resource.Error -> {
                emit(Data(EmitType.Loading, false))
            }
            else -> {}
        }
    }




}