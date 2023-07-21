package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.GstListResponse
import retrofit2.http.GET

interface GstInitialApi {

    @GET("/e1424da24560360b8d18")
    suspend fun getGst() : GstListResponse
}