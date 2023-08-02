package com.jaya.app.store.core.usecase

import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.domain.repositories.DashboardRepository
import com.jaya.app.store.core.utils.helper.AppNavigator
import com.jaya.app.store.core.utils.helper.AppStore
import com.jaya.app.store.core.utils.helper.NavigationIntent
import kotlinx.coroutines.flow.flow
import okhttp3.Route
import javax.inject.Inject

class DashboardUseCase @Inject constructor(
    private  val repository: DashboardRepository,
    private  val appStore: AppStore,
    private  val appNavigator: AppNavigator
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


    fun logout() = flow {
        if (appStore.logout()) {
            emit(
                Data(
                    EmitType.NAVIGATE,
                   appNavigator.tryNavigateTo(
                       route = Destination.MobileNumberScreen(),
                       popUpToRoute = "",
                       inclusive = true,
                       isSingleTop = true
                   )
            )
            )
        }
    }




}