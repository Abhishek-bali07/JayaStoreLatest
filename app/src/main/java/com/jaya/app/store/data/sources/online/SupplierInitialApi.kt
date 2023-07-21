package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.SupplierDetailResponse
import retrofit2.http.GET

interface SupplierInitialApi {

    @GET("/0a6de7a740747434d0a9")
    suspend fun getSupplierData(): SupplierDetailResponse
}