package com.hads.digikala.ui.screens.home


import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digikala.data.remote.NetworkResult
import com.hads.digikala.ui.theme.spacing
import com.hads.digikala.viewmodel.HomeViewModel
import com.hads.digikala.R
import com.hads.digikala.data.model.home.AmazingItem
import com.hads.digikala.ui.theme.DarkCyan
import com.hads.digikala.ui.theme.darkText

@Composable
fun MostFavoriteProductSection(
    viewModel: HomeViewModel = hiltViewModel()
) {
    var mostFavoriteList by remember {
        mutableStateOf<List<AmazingItem>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }

    val mostFavoriteResult by viewModel.mostFavoriteItems.collectAsState()
    when (mostFavoriteResult) {
        is NetworkResult.Success -> {
            mostFavoriteList = mostFavoriteResult.data ?: emptyList()
            loading = false
        }
        is NetworkResult.Error -> {
            loading = false
            Log.e("3636", "MostFavoriteProductSection error : ${mostFavoriteResult.message}")
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

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.favorite_product),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

            Text(
                text = stringResource(id = R.string.show_all),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.DarkCyan,
            )

        }


        LazyRow {
            items(mostFavoriteList) { item ->
                MostFavoriteProductsOffer(item)
            }
            item{
                MostFavoriteProductsShowMore()
            }
        }

    }


}
