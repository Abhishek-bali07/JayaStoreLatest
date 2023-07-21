package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.ProductDetailResponse
import retrofit2.http.GET

interface ProductInitialApi {

    @GET("/a1ff67a687e9321dae84")
    suspend fun getProduct(): ProductDetailResponse
}