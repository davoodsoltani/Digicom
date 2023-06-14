package com.hads.digikala.ui.screens.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.navArgument
import com.hads.digikala.R
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.theme.digikalaLightRed
import com.hads.digikala.viewmodel.HomeViewModel

@Composable
fun AmazingOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var amazingItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val amazingItemResult by viewModel.amazingItem.collectAsState()
    when (amazingItemResult) {
        is NetworkResult.Success -> {
            amazingItemList = amazingItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("error", "top slider error: ${amazingItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.digikalaLightRed)
            .fillMaxWidth()
    ) {
        LazyRow(modifier = Modifier.background(MaterialTheme.colors.digikalaLightRed)) {
            item {
                AmazingOfferCard(
                    topImageResId = R.drawable.amazings,
                    bottomImageResId = R.drawable.box
                )
            }

            items(amazingItemList){item ->
                AmazingItem(item = item, navController = navController)
            }

            item {
                AmazingShowMorItem()
            }
        }

    }
}