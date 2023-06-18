package com.hads.digicom.ui.screens.profile


import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digicom.R
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.ui.theme.selectedBottomBar
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.utils.InputValidation
import com.hads.digicom.utils.InputValidation.isValidPassword
import com.hads.digicom.viewmodel.DataStoreViewModel
import com.hads.digicom.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel()

) {

    val context = LocalContext.current



    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {

                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {

                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhoneNumber(user.phone)
                            dataStore.saveUserPassword(profileViewModel.inputPasswordState)

                            profileViewModel.screenState = ProfileScreenState.PROFILE_STATE
                        }
                    }
                    Toast.makeText(
                        context,
                        loginResponse.message,
                        Toast.LENGTH_LONG
                    ).show()
                    profileViewModel.loadingState = false
                }
                is NetworkResult.Error -> {
                    profileViewModel.loadingState = false
                    Log.e("3636", "loginResponse error : ${loginResponse.message}")
                }
                is NetworkResult.Loading -> {}
            }
        }

    }




    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { }) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.digi_settings
                    ), contentDescription = "",
                    modifier = Modifier
                        .padding(
                            MaterialTheme.spacing.small
                        )
                        .size(MaterialTheme.spacing.samiLarge),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    modifier = Modifier
                        .padding(MaterialTheme.spacing.small),
                    tint = MaterialTheme.colors.selectedBottomBar
                )
            }
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

        Text(
            text = stringResource(id = R.string.set_password_text),
            modifier = Modifier.padding(
                horizontal = MaterialTheme.spacing.samiLarge
            ),
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.darkText,
            fontWeight = FontWeight.Bold
        )

        MyEditText(
            value = profileViewModel.inputPhoneState,
            placeholder = stringResource(id = R.string.phone_and_email),
            onValueChange = {},
        )


        MyEditText(
            value = profileViewModel.inputPasswordState,
            placeholder = stringResource(id = R.string.set_password),
            onValueChange = {
                profileViewModel.inputPasswordState = it
            }
        )


        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        if (profileViewModel.loadingState) {
            LoadingButton()
        } else {
            MyButton(text = stringResource(id = R.string.digicom_login)) {
                if (isValidPassword(profileViewModel.inputPasswordState)) {

                    profileViewModel.login()

                } else {
                    Toast.makeText(
                        context,
                        context.resources.getText(R.string.password_format_error),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }


    }


}
