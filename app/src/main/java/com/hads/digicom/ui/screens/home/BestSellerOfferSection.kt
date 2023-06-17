package com.hads.digicom.ui.screens.home


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digicom.R
import com.hads.digicom.data.model.home.AmazingItem
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.utils.DigitHelper.digitByLocate
import com.hads.digicom.viewmodel.HomeViewModel

@Composable
fun BestSellerOfferSection(
    viewModel: HomeViewModel = hiltViewModel()
) {
    var bestSellerOfferList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val bestSellerOfferResult by viewModel.bestsellerProducts.collectAsState()
    when (bestSellerOfferResult) {
        is NetworkResult.Success -> {
            bestSellerOfferList = bestSellerOfferResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "BestSellerOfferSection error : ${bestSellerOfferResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Text(
            text = stringResource(id = R.string.best_selling_products),
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h3,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colors.darkText,
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier
                .padding(top = MaterialTheme.spacing.medium)
                .height(250.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            itemsIndexed(bestSellerOfferList) { index, item ->
                ProductHorizontalCard(
                    name = item.name,
                    id = digitByLocate((index + 1).toString()),
                    imageUrl = item.image
                )
            }

        }

    }
}

