package com.jaya.app.store.core.utils.helper

interface AppStore {
    suspend fun intro(status: Boolean)

    suspend fun isIntroDone(): Boolean

    suspend fun login(userId:String,)

    suspend fun userId(): String

    suspend fun logout()

    suspend fun isLoggedIn(): Boolean


    suspend fun storeFCMToken(token: String)

    suspend fun lastFCMToken(): String

    suspend fun removeLastFCMToken(): Boolean
}