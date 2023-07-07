package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.AppVersionResponse
import retrofit2.http.GET


interface AppVersionApi {


    @GET("/256371f25b5a2d3e804b")
    suspend fun getVersion(): AppVersionResponse

}