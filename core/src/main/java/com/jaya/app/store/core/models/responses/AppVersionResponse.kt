package com.jaya.app.store.core.models.responses


import com.jaya.app.store.core.entities.AppVersion


data class AppVersionResponse(
    val status: Boolean,
    val message: String,
    val appVersion: AppVersion
)