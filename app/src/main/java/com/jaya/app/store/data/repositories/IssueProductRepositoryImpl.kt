package com.jaya.app.store.data.repositories

import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.domain.repositories.IssueProductRepository
import com.jaya.app.store.core.models.responses.PlantListResponse
import com.jaya.app.store.core.models.responses.ProductDetailResponse
import com.jaya.app.store.core.models.responses.SectionListResponse
import com.jaya.app.store.core.models.responses.SubSectionListResponse
import com.jaya.app.store.data.sources.online.PlantInitialApi
import com.jaya.app.store.data.sources.online.ProductInitialApi
import com.jaya.app.store.data.sources.online.SectionInitialApi
import com.jaya.app.store.data.sources.online.SubSectionApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IssueProductRepositoryImpl @Inject constructor(
    val productInitialApi: ProductInitialApi,
    val plantInitialApi: PlantInitialApi,
    val sectionInitialApi: SectionInitialApi,
    val subsectionApi: SubSectionApi
) : IssueProductRepository {





    override suspend fun getProduct(
        userId: String
    ): Resource<ProductDetailResponse> {
        return  try {
            val result = productInitialApi.getProduct();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    override suspend fun getSection(
        userId: String
    ): Resource<SectionListResponse> {
        return  try {
            val result = sectionInitialApi.getSectionData();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    override suspend fun getPlant(
        userId: String
    ): Resource<PlantListResponse> {
        return  try {
            val result = plantInitialApi.getPlants();
            Resource.Success(result)
        }catch (ex: HttpException) {
            Resource.Error(ex.message())
        } catch (ex: IOException) {
            Resource.Error(ex.message.toString())
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    override suspend fun getSubsection(
        userId: String
    ): Resource<SubSectionListResponse> {
        return  try {
            val result = subsectionApi.getSubSection();
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