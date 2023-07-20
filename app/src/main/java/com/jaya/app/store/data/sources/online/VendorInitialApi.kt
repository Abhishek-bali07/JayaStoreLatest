package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.VendorDetailResponse
import retrofit2.http.GET

interface VendorInitialApi {

    @GET("/0a6de7a740747434d0a9")
    suspend fun getVendorData(): VendorDetailResponse
}