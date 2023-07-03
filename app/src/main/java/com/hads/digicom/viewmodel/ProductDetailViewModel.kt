package com.hads.digicom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digicom.data.model.home.StoreProduct
import com.hads.digicom.data.model.product_detail.NewComment
import com.hads.digicom.data.model.product_detail.ProductDetail
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: ProductDetailRepository) :
    ViewModel() {

    val productDetail = MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())

    val similarProducts =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())

    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    fun getProductById(productId: String) {
        viewModelScope.launch {
            productDetail.emit(repository.getProductById(productId))
        }
    }

    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProducts.emit(repository.getSimilarProducts(categoryId))
        }
    }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }


}