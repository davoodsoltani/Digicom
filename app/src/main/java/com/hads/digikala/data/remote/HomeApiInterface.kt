package com.hads.digikala.data.remote

import com.hads.digikala.data.model.ResponseResult
import com.hads.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("v1/getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>
}