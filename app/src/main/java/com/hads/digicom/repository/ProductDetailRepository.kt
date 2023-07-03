package com.hads.digicom.repository

import com.hads.digicom.data.model.home.StoreProduct
import com.hads.digicom.data.model.product_detail.NewComment
import com.hads.digicom.data.model.product_detail.ProductDetail
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.data.remote.ProductDetailApiInterface
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val api: ProductDetailApiInterface) :
    BaseApiResponse() {

    suspend fun getProductById(productId: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(productId)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }


}