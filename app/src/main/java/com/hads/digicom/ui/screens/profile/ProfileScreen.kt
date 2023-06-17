package com.hads.digicom.ui.screens.profile

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hads.digicom.MainActivity
import com.hads.digicom.utils.Constants
import com.hads.digicom.viewmodel.DataStoreViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Profile Screen")

        val activity = LocalContext.current as Activity

        Button(
            onClick = {
                activity.apply {
                    dataStore.saveUserLanguage(Constants.ENGLISH_LANG)
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            } ) {
            Text(text = "En")
        }
        Button(
            onClick = {
                activity.apply {
                    dataStore.saveUserLanguage(Constants.PERSIAN_LANG)
                    finish()
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            } ) {
            Text(text = "Fa")
        }
    }
}