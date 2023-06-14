package com.hads.digikala.repository

import com.hads.digikala.data.model.category.SubCategory
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.model.home.MainCategory
import com.hads.digikala.data.model.home.Slider
import com.hads.digikala.data.remote.BaseApiResponse
import com.hads.digikala.data.remote.CategoryApiInterface
import com.hads.digikala.data.remote.HomeApiInterface
import com.hads.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) : BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }
}