package com.hads.digicom.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hads.digicom.ui.theme.*
import com.hads.digicom.viewmodel.DataStoreViewModel
import com.hads.digicom.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel(),
    profileViewModel: ProfileViewModel = hiltViewModel()
) {


    if (!dataStore.getUserToken().isNullOrBlank()) {
        Profile()
    } else {
        when (profileViewModel.screenState) {
            ProfileScreenState.LOGIN_STATE -> {
                LoginScreen()
            }
            ProfileScreenState.PROFILE_STATE -> {
                Profile()
            }
            ProfileScreenState.REGISTER_STATE -> {
                RegisterScreen()
            }
        }
    }


}

@Composable
fun Profile() {
    Text(text = "Profile")
}
