package com.jaya.app.store.data.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.AddProductRepository
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.models.responses.GstListResponse
import com.jaya.app.store.core.models.responses.SupplierDetailResponse
import com.jaya.app.store.core.models.responses.addProductResponse
import com.jaya.app.store.data.sources.online.AddProductApi
import com.jaya.app.store.data.sources.online.GstInitialApi
import com.jaya.app.store.data.sources.online.SupplierInitialApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    val supplierInitialApi : SupplierInitialApi,
    val gstInitialApi : GstInitialApi,
    val addProductApi: AddProductApi
):AddProductRepository {


    override suspend fun addProductData(
        userId: String,
        uploadData: UploadData
    ): Resource<addProductResponse> {
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

    override suspend fun getSupplier(
        userId: String
    ): Resource<SupplierDetailResponse> {
        return  try {
            val result = supplierInitialApi.getSupplierData();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    override suspend fun getGst(
        userId: String
    ): Resource<GstListResponse> {
        return  try {
            val result = gstInitialApi.getGst();
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