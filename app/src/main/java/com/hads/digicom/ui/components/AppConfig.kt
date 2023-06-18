package com.hads.digicom.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.utils.Constants
import com.hads.digicom.utils.Constants.USER_ID
import com.hads.digicom.utils.Constants.USER_LANGUAGE
import com.hads.digicom.utils.Constants.USER_PASSWORD
import com.hads.digicom.utils.Constants.USER_PHONE
import com.hads.digicom.utils.Constants.USER_TOKEN
import com.hads.digicom.utils.InputValidation.isValidPassword
import com.hads.digicom.utils.InputValidation.isValidPhoneNumber
import com.hads.digicom.viewmodel.DataStoreViewModel
import com.hads.digicom.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()
) {

    getDataStoreVariables(dataStore)

    if (isValidPhoneNumber(USER_PHONE) && isValidPassword(USER_PASSWORD)) {
        profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)

        LaunchedEffect(Dispatchers.Main) {
            profileViewModel.loginResponse.collectLatest { loginResponse ->
                when (loginResponse) {
                    is NetworkResult.Success -> {
                        loginResponse.data?.let { user ->
                            if (user.token.isNotEmpty()) {
                                dataStore.saveUserToken(user.token)
                                dataStore.saveUserId(user.id)
                                dataStore.saveUserPhoneNumber(user.phone)
                                dataStore.saveUserPassword(USER_PASSWORD)

                                getDataStoreVariables(dataStore)

                                Log.e("3636", "refresh token")
                            }

                        }
                    }

                    else -> {}
                }
            }

        }
    }

}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)

    USER_PHONE = dataStore.getUserPhoneNumber().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
}
