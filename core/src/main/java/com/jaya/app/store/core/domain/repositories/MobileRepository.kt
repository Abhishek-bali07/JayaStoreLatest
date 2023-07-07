package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.models.responses.MobileVerifyResponse
import com.jaya.app.store.core.models.responses.OtpVerificationResponse


interface MobileRepository {
    suspend fun login(mobileNumber: String): Resource<MobileVerifyResponse>


    suspend fun verify(mobileNumber: String, otp: String): Resource<OtpVerificationResponse>
}