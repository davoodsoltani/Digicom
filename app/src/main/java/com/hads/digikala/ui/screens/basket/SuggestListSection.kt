package com.hads.digikala.ui.screens.basket


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.theme.darkText
import com.hads.digikala.ui.theme.searchBarBg
import com.hads.digikala.ui.theme.spacing
import com.hads.digikala.viewmodel.BasketViewModel
import com.hads.digikala.R
import com.hads.digikala.data.model.basket.CartItem
import com.hads.digikala.data.model.basket.CartStatus
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.ui.screens.home.MostDiscountedCard

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SuggestListSection(
    viewModel: BasketViewModel = hiltViewModel()
) {


    LaunchedEffect(key1 = true) {
        viewModel.getAllProducts()
    }

    var suggestedList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val suggestedItemResult by viewModel.suggestedList.collectAsState()
    when (suggestedItemResult) {
        is NetworkResult.Success -> {
            suggestedList = suggestedItemResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "SuggestListSection error : ${suggestedItemResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .background(MaterialTheme.colors.searchBarBg)
    )

    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium),
        text = stringResource(id = R.string.suggestion_for_you),
        textAlign = TextAlign.Right,
        style = MaterialTheme.typography.h4,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colors.darkText,
    )

    FlowRow(
        maxItemsInEachRow = 2,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Start
    ) {

        for (item in suggestedList) {
            SuggestionItemCard(item = item){
                viewModel.insertCartItem(
                    CartItem(
                        it._id,
                        it.name,
                        it.seller,
                        it.price,
                        it.discountPercent,
                        it.image,
                        1,
                        CartStatus.CURRENT_CART
                    )
                )

            }
        }

    }


}
