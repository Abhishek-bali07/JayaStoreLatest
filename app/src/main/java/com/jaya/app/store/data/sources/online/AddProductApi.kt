package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.addProductResponse
import retrofit2.http.POST

interface AddProductApi {

    @POST("/1599795e6ff18a5aa4ea")
    suspend fun addData(): addProductResponse
}