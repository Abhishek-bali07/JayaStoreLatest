package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.DashboardDataResponse
import retrofit2.http.GET

interface DashboardApi {

    @GET("/192a8696331cf3319694")
    suspend fun  getStockData(): DashboardDataResponse
}