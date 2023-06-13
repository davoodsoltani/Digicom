package com.hads.digikala.data.remote

import android.util.Log
import com.hads.digikala.data.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<ResponseResult<T>>): NetworkResult<T> =
        withContext(Dispatchers.IO){
            try {
                val response = apiCall()
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(body.data, body.message)
                    }
                }
                return@withContext error("erro code: ${response.code()}, error message: ${response.message()}")
            }catch (e: Exception){
                return@withContext error("error exception: ${e.message}")
            }
        }



    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(null, errorMessage)
}