package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.models.responses.ItemListResponse
import com.jaya.app.store.core.models.responses.VendorDetailResponse
import com.jaya.app.store.core.models.responses.addProductResponse

interface AddProductRepository {
    suspend fun addProductData(userId: String, uploadData: UploadData):Resource<addProductResponse>

    suspend fun  getVendor(userId: String):Resource<VendorDetailResponse>

    suspend fun  getItem(userId: String):Resource<ItemListResponse>

}