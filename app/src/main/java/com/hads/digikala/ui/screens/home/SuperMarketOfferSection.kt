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
import com.hads.digikala.R
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.theme.DigikalaLightGreen
import com.hads.digikala.ui.theme.digikalaLightRed
import com.hads.digikala.viewmodel.HomeViewModel

@Composable
fun SuperMarketOfferSection(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
){

    var superMarketItemList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val superMarketItemResult by viewModel.superMarketItems.collectAsState()
    when (superMarketItemResult) {
        is NetworkResult.Success -> {
            superMarketItemList = superMarketItemResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "superMarketOfferSection error : ${superMarketItemResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.digikalaLightRed)
    ) {

        LazyRow(modifier = Modifier.background(MaterialTheme.colors.DigikalaLightGreen)) {

            item {
                AmazingOfferCard(R.drawable.supermarketamazings, R.drawable.fresh)
            }

            items(superMarketItemList) { item ->
                AmazingItem(item = item ,  navController = navController)
            }

            item {
                AmazingShowMorItem()
            }


        }

    }

}
