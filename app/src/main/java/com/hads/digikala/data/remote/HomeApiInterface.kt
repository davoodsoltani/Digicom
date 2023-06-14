package com.hads.digikala.data.remote

import com.hads.digikala.data.model.ResponseResult
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.model.home.MainCategory
import com.hads.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

    @GET("v1/getAmazingProducts")
    suspend fun getAmazingProducts(): Response<ResponseResult<List<AmazingItem>>>

    @GET("v1/getSuperMarketAmazingProducts")
    suspend fun getSuperMarketAmazingProducts(): Response<ResponseResult<List<AmazingItem>>>
    @GET("v1/get4Banners")
    suspend fun get4Banners(): Response<ResponseResult<List<Slider>>>

    @GET("v1/getCategories")
    suspend fun getCategories(): Response<ResponseResult<List<MainCategory>>>

    @GET("v1/getCenterBanners")
    suspend fun getCenterBanners(): Response<ResponseResult<List<Slider>>>

}
