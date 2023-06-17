package com.hads.digikala.ui.screens.basket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hads.digikala.R
import com.hads.digikala.ui.theme.digikalaRed
import com.hads.digikala.ui.theme.localSpacing
import com.hads.digikala.ui.theme.spacing
import com.hads.digikala.viewmodel.BasketViewModel

@Composable
fun BasketScreen(navController: NavHostController) {
    Basket(navController = navController)
}

@Composable
fun Basket(
    navController: NavHostController,
    viewModel: BasketViewModel = hiltViewModel()
) {
    var selectTabIndex by remember {
        mutableStateOf(0)
    }

    var tabTitle = listOf(
        stringResource(id = R.string.cart),
        stringResource(id = R.string.next_cart_list)
    )

    Column {
        TabRow(
            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium),
            selectedTabIndex = selectTabIndex,
            contentColor = MaterialTheme.colors.digikalaRed,
            backgroundColor = Color.White,
            indicator = { line ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(line[selectTabIndex])
                        .height(3.dp)
                        .background(Color.Red)
                )
            }
        ) {
            tabTitle.forEachIndexed { index, title ->
                Tab(selected = (selectTabIndex == index),
                    onClick = {
                        selectTabIndex = index
                    },
                    selectedContentColor = MaterialTheme.colors.digikalaRed,
                    unselectedContentColor = Color.Gray,
                    text = {
                        Row {
                            Text(
                                text = title,
                                style = MaterialTheme.typography.h6,
                                fontWeight = FontWeight.SemiBold
                            )

                        }
                    }
                )
            }
        }

        when(selectTabIndex){
            0 -> ShoppingCart()
            1 -> NextShoppingList()
        }
    }

}