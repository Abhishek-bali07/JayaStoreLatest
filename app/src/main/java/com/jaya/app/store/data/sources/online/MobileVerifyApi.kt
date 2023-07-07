package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.MobileVerifyResponse
import retrofit2.http.GET

interface MobileVerifyApi {

    @GET("/c5c4317673d615f922e9")
    suspend fun verifyMobile() : MobileVerifyResponse


}