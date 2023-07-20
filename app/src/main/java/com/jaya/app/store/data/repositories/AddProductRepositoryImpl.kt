package com.jaya.app.store.data.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.AddProductRepository
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.models.responses.ItemListResponse
import com.jaya.app.store.core.models.responses.VendorDetailResponse
import com.jaya.app.store.core.models.responses.addProductResponse
import com.jaya.app.store.data.sources.online.AddProductApi
import com.jaya.app.store.data.sources.online.StockInitialApi
import com.jaya.app.store.data.sources.online.VendorInitialApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddProductRepositoryImpl @Inject constructor(
    val vendorInitialApi : VendorInitialApi,
    val stockInitialApi : StockInitialApi,
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

    override suspend fun getVendor(userId: String): Resource<VendorDetailResponse> {
        return  try {
            val result = vendorInitialApi.getVendorData();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    override suspend fun getItem(userId: String): Resource<ItemListResponse> {
        return  try {
            val result = stockInitialApi.getStockItem();
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