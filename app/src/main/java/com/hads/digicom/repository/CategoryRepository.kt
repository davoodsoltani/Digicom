package com.hads.digicom.repository

import com.hads.digicom.data.model.category.SubCategory
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.CategoryApiInterface
import com.hads.digicom.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) : BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }
}