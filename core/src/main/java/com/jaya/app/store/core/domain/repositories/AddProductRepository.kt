package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.models.responses.StockListResponse
import com.jaya.app.store.core.models.responses.VendorListResponse
import com.jaya.app.store.core.models.responses.addProductResponse

interface AddProductRepository {
    suspend fun addProductItem():Resource<addProductResponse>

    suspend fun getVendorItem(userId: String):Resource<VendorListResponse>

    suspend fun getStockItem(userId: String):Resource<StockListResponse>
}