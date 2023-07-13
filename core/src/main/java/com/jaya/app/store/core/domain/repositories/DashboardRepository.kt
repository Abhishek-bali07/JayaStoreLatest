package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.models.responses.DashboardDataResponse

interface DashboardRepository {


    suspend fun  getStock(userId: String):Resource<DashboardDataResponse>
}