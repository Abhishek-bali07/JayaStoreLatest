package com.jaya.app.store.data.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.AddProductRepository
import com.jaya.app.store.core.models.responses.addProductResponse
import com.jaya.app.store.data.sources.online.AddProductApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    val addProductApi: AddProductApi
):AddProductRepository {
    override suspend fun addProductItem(): Resource<addProductResponse> {
       return  try {
           val result = addProductApi.addData();
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