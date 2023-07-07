package com.jaya.app.store.data.repositories


import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.SplashRepository
import com.jaya.app.store.core.models.responses.AppVersionResponse
import com.jaya.app.store.data.sources.online.AppVersionApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    val appVersionApi: AppVersionApi
    ) : SplashRepository {
    override suspend fun appVersion(currentVersion: Int): Resource<AppVersionResponse> {
        return try{
            val  reslt = appVersionApi.getVersion();
            Resource.Success(reslt)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }
}