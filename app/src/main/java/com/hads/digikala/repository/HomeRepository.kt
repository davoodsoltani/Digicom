package com.hads.digikala.repository

import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.model.home.MainCategory
import com.hads.digikala.data.model.home.Slider
import com.hads.digikala.data.remote.BaseApiResponse
import com.hads.digikala.data.remote.HomeApiInterface
import com.hads.digikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }
    suspend fun getAmazingProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAmazingProducts()
        }

    suspend fun getSuperMarketAmazingProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getSuperMarketAmazingProducts()
        }
    suspend fun get4Banners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.get4Banners()
        }
    suspend fun getCategories(): NetworkResult<List<MainCategory>> =
        safeApiCall {
            api.getCategories()
        }
    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }
}