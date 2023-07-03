package com.hads.digicom.ui.screens.product_detail

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hads.digicom.data.model.home.StoreProduct
import com.hads.digicom.data.remote.NetworkResult
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.R
import com.hads.digicom.ui.screens.home.MostFavoriteProductsOffer
import com.hads.digicom.ui.screens.home.MostFavoriteProductsShowMore
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.viewmodel.ProductDetailViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SimilarProductSection(
    categoryId: String,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {

    var similarList by remember {
        mutableStateOf<List<StoreProduct>>(emptyList())
    }
    var loading by remember {
        mutableStateOf(false)
    }
    viewModel.getSimilarProducts(categoryId)
    LaunchedEffect(true) {

        viewModel.similarProducts.collectLatest { similarListResult ->
            when (similarListResult) {
                is NetworkResult.Success -> {
                    similarList = similarListResult.data ?: emptyList()
                    loading = false
                }
                is NetworkResult.Error -> {
                    loading = false
                    Log.e("3636", "SimilarProductSection error : ${similarListResult.message}")
                }
                is NetworkResult.Loading -> {
                    loading = true
                }
            }
        }

    }


    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.small)
            .alpha(0.4f)
            .shadow(2.dp),
        color = Color.LightGray,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.small)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.similar_product),
                style = MaterialTheme.typography.h3,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText,
            )

        }


        LazyRow {
            items(similarList) { item ->
                MostFavoriteProductsOffer(item)
            }
            item {
                MostFavoriteProductsShowMore()
            }
        }

    }


}