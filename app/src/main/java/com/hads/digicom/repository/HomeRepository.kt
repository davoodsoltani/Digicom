package com.hads.digicom.repository

import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.model.home.MainCategory
import com.hads.digicom.data.model.home.Slider
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.HomeApiInterface
import com.hads.digicom.data.remote.NetworkResult
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
    suspend fun getBestsellerProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getBestsellerProducts()
        }
    suspend fun getMostVisitedProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostVisitedProducts()
        }
    suspend fun getMostFavoriteProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostFavoriteProducts()
        }
    suspend fun getMostDiscountedProducts(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getMostDiscountedProducts()
        }
}