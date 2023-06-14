package com.hads.digikala.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digikala.utils.Constants
import com.hads.digikala.viewmodel.DataStoreViewModel
import kotlinx.coroutines.runBlocking

@Composable
fun AppConfig(
    dataStore: DataStoreViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        getDatastoreVariable(dataStore)
    }
}

private fun getDatastoreVariable(dataStore: DataStoreViewModel) {
        Constants.USER_LANGUAGE = dataStore.getUserLanguage()
        dataStore.saveUserLanguage(Constants.USER_LANGUAGE)
}