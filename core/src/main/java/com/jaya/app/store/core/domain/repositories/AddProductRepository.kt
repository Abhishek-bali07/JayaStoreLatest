package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.entities.UploadData
import com.jaya.app.store.core.models.responses.GstListResponse
import com.jaya.app.store.core.models.responses.SupplierDetailResponse
import com.jaya.app.store.core.models.responses.addProductResponse

interface AddProductRepository {
    suspend fun addProductData(userId: String, uploadData: UploadData):Resource<addProductResponse>

    suspend fun  getSupplier(userId: String):Resource<SupplierDetailResponse>

    suspend fun  getGst(userId: String):Resource<GstListResponse>

}