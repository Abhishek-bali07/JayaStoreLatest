package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.SubSectionListResponse
import retrofit2.http.GET

interface SubSectionApi {



    @GET("/9e25c446af9856adfb1b")
    suspend fun getSubSection(): SubSectionListResponse
}