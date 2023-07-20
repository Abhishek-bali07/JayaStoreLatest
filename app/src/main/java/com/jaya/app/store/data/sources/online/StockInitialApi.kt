package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.ItemListResponse
import retrofit2.http.GET

interface StockInitialApi {

    @GET("/e1424da24560360b8d18")
    suspend fun getStockItem() : ItemListResponse
}