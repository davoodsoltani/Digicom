package com.hads.digicom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.model.home.MainCategory
import com.hads.digicom.data.model.home.Slider
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItem = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val category = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val centerBanner = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestsellerProducts = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostVisitedItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostFavoriteItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val mostDiscountedItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {
            launch {
                slider.emit(repository.getSlider())
            }
            launch {
                amazingItem.emit(repository.getAmazingProducts())
            }
            launch {
                superMarketItems.emit(repository.getSuperMarketAmazingProducts())
            }
            launch {
                banners.emit(repository.get4Banners())
            }
            launch {
                category.emit(repository.getCategories())
            }
            launch {
                centerBanner.emit(repository.getCenterBanners())
            }
            launch {
                bestsellerProducts.emit(repository.getBestsellerProducts())
            }
            launch {
                mostVisitedItems.emit(repository.getMostVisitedProducts())
            }
            launch {
                mostFavoriteItems.emit(repository.getMostFavoriteProducts())
            }
            launch {
                mostDiscountedItems.emit(repository.getMostDiscountedProducts())
            }
        }
    }
}