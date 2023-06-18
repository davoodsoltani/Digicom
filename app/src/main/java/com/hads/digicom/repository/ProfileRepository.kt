package com.hads.digicom.repository

import com.hads.digicom.data.model.profile.LoginRequest
import com.hads.digicom.data.model.profile.LoginResponse
import com.hads.digicom.data.remote.BaseApiResponse
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.data.remote.ProfileApiInterface
import javax.inject.Inject


class ProfileRepository @Inject constructor(private val api: ProfileApiInterface) : BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }


}
