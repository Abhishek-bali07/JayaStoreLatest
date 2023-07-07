package com.jaya.app.store.core.models.responses

data class MobileVerifyResponse(
    val status: Boolean,
    val message: String,
    val isSend: Boolean,
)
