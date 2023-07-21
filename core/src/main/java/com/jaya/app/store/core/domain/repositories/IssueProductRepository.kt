package com.jaya.app.store.core.domain.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.models.responses.PlantListResponse
import com.jaya.app.store.core.models.responses.ProductDetailResponse
import com.jaya.app.store.core.models.responses.SectionListResponse
import com.jaya.app.store.core.models.responses.SubSectionListResponse

interface IssueProductRepository {

    suspend fun  getProduct(userId: String): Resource<ProductDetailResponse>

    suspend fun  getSection(userId: String): Resource<SectionListResponse>


    suspend fun  getPlant(userId: String): Resource<PlantListResponse>


    suspend fun  getSubsection(userId: String): Resource<SubSectionListResponse>


}