package com.hads.digicom.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hads.digicom.utils.Constants
import com.hads.digicom.utils.LocaleUtils
import com.hads.digicom.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController = navController)
}

@Composable
fun Home(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LocaleUtils.setLocale(LocalContext.current, Constants.USER_LANGUAGE)
    LaunchedEffect(true) {
        refreshDataFromServer(viewModel)
    }

    SwipeRefreshSection(navController = navController, viewModel = viewModel)
}

@Composable
private fun SwipeRefreshSection(navController: NavHostController, viewModel: HomeViewModel) {
    val refreshScope = rememberCoroutineScope()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            refreshScope.launch {
                refreshDataFromServer(viewModel)
                Log.e("swipRefresh", "swipeRefreshState")
            }
        }) {

        LazyColumn(modifier = Modifier.fillMaxWidth().padding(bottom = 60.dp)) {
            item { SearchBarSection() }
            item { TopSliderSection() }
            item { ShowCaseSection(navController) }
            item { AmazingOfferSection(navController) }
            item { ProposalCardSection() }
            item { SuperMarketOfferSection(navController) }
            item { CategoryListSection() }
            item { CenterBannerSection(1) }
            item { BestSellerOfferSection() }
            item { CenterBannerSection(2) }
            item { MostFavoriteProductSection() }
            item { CenterBannerSection(3) }
            item { MostVisitedOfferSection() }
            item { CenterBannerSection(4) }
            item { CenterBannerSection(5) }
            item { MostDiscountedSection() }
        }
    }
}

private suspend fun refreshDataFromServer(viewModel: HomeViewModel) {
    viewModel.getAllDataFromServer()
}