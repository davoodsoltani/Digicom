package com.hads.digikala.data.remote

import com.hads.digikala.data.model.ResponseResult
import com.hads.digikala.data.model.category.SubCategory
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.model.home.MainCategory
import com.hads.digikala.data.model.home.Slider
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiInterface {

    @GET("v1/getSubCategories")
    suspend fun getSubCategories(): Response<ResponseResult<SubCategory>>

}
