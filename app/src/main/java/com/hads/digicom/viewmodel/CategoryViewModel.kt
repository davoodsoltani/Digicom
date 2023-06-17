package com.hads.digicom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digicom.data.model.category.SubCategory
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.repository.CategoryRepository
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