package com.hads.digikala.ui.screens.home


import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digikala.data.model.home.Slider
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.components.CenterBannerItem
import com.hads.digikala.viewmodel.HomeViewModel

@Composable
fun CenterBannerSection(
    bannerNumber: Int,
    viewModel: HomeViewModel = hiltViewModel()
) {
    var centerBannerList by remember {
        mutableStateOf<List<Slider>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val centerBannerResult by viewModel.centerBanner.collectAsState()
    when (centerBannerResult) {
        is NetworkResult.Success -> {
            centerBannerList = centerBannerResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "CenterBannerItem error : ${centerBannerResult.message}")
        }
        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (centerBannerList.isNotEmpty()) {
        CenterBannerItem(imageUrl = centerBannerList[bannerNumber - 1].image)
    }


}
