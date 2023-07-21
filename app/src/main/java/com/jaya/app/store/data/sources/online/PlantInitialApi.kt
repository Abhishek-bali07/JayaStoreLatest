package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.PlantListResponse
import retrofit2.http.GET

interface PlantInitialApi {


    @GET("/129a70cf93f3b6db77db")
    suspend fun getPlants(): PlantListResponse
}