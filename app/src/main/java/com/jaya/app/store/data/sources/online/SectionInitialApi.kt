package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.SectionListResponse
import com.jaya.app.store.core.models.responses.SupplierDetailResponse
import retrofit2.http.GET

interface SectionInitialApi {


    @GET("/99f86600aeab56bc2c50")
    suspend fun getSectionData(): SectionListResponse
}