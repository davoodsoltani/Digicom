package com.hads.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digikala.data.model.category.SubCategory
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.model.home.MainCategory
import com.hads.digikala.data.model.home.Slider
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.repository.CategoryRepository
import com.hads.digikala.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    val category = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())

    suspend fun getAllDataFromServer() {
        viewModelScope.launch {
            launch {
                category.emit(repository.getSubCategories())
            }
        }
    }
}