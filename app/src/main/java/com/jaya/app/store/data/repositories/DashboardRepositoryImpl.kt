package com.jaya.app.store.data.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.DashboardRepository
import com.jaya.app.store.core.models.responses.DashboardDataResponse
import com.jaya.app.store.data.sources.online.DashboardApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DashboardRepositoryImpl @Inject constructor(
  val dashboardApi : DashboardApi
) :DashboardRepository{
    override suspend fun getStock(userId: String): Resource<DashboardDataResponse> {
        return  try {
            val result = dashboardApi.getStockData();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }
}