package com.jaya.app.store.data.sources.online

import com.jaya.app.store.core.models.responses.OtpVerificationResponse
import retrofit2.http.GET

interface SendOtpApi {

    @GET("/da38f6f5b04229a89d00")
    suspend fun sendOtpVerify(

    ): OtpVerificationResponse

}