package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.models.responses.AppVersionResponse


interface SplashRepository {


    suspend fun  appVersion(currentVersion: Int) : Resource<AppVersionResponse>
}