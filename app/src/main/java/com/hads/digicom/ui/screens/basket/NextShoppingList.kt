package com.hads.digicom.ui.screens.basket


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hads.digicom.R
import com.hads.digicom.data.model.basket.CartItem
import com.hads.digicom.data.model.basket.CartStatus
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.utils.Constants
import com.hads.digicom.viewmodel.BasketViewModel

@Composable
fun NextShoppingList(
    navController: NavController,
    viewModel: BasketViewModel = hiltViewModel()
) {

    val nextCartItemsState: BasketScreenState<List<CartItem>> by viewModel.nextCartItems
        .collectAsState(BasketScreenState.Loading)




    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 60.dp),
    ) {

        item {
            if (Constants.USER_TOKEN == "null") {
                LoginOrRegisterSection(navController)
            }
        }

        when (nextCartItemsState) {
            is BasketScreenState.Success -> {
                if ((nextCartItemsState as BasketScreenState.Success<List<CartItem>>).data.isEmpty()) {
                    item { EmptyNextShoppingList() }
                } else {
                    items((nextCartItemsState as BasketScreenState.Success<List<CartItem>>).data) { item ->
                        CartItemCard(item, CartStatus.NEXT_CART)
                    }
                }
            }
            is BasketScreenState.Loading -> {
                item {
                    Column(
                        modifier = Modifier
                            .height(LocalConfiguration.current.screenHeightDp.dp - 60.dp)
                            .fillMaxWidth()
                            .padding(vertical = MaterialTheme.spacing.small),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.please_wait),
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h5,
                            color = MaterialTheme.colors.darkText,
                        )
                    }
                }
            }
            is BasketScreenState.Error -> {
                Log.e("3636", "err")
            }


        }
    }


}
