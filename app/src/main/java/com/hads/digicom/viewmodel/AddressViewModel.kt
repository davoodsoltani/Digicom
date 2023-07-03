package com.hads.digicom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hads.digicom.data.model.address.UserAddress
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.repository.AddressRepository
import com.hads.digicom.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())


    init {
        getUserAddressList(Constants.USER_TOKEN)
    }

    private fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

}