package com.hads.digicom.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.hads.digicom.R
import com.hads.digicom.navigation.Screen
import com.hads.digicom.ui.components.RoundedIconBox
import com.hads.digicom.ui.theme.amber
import com.hads.digicom.ui.theme.grayCategory
import com.hads.digicom.ui.theme.localSpacing
import com.hads.digicom.utils.Constants

@Composable
fun ShowCaseSection(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = localSpacing.current.biggerSmall,
                vertical = localSpacing.current.samiMedium
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = localSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                image = painterResource(id = R.drawable.digijet),
                title = stringResource(id = R.string.digicom_jet),
                onClick = onBoxClick(navController,Constants.DIGIJET_URL),
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.auction),
                title = stringResource(id = R.string.digi_style),
                onClick = onBoxClick(navController,Constants.AUCTION_URL)
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digipay),
                title = stringResource(id = R.string.digi_pay),
                onClick = onBoxClick(navController,Constants.DIGIPAY_URL)
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.pindo),
                title = stringResource(id = R.string.pindo),
                bgColor = MaterialTheme.colors.amber,
                onClick = onBoxClick(navController,Constants.PINDO_URL)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = localSpacing.current.semiSmall),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedIconBox(
                image = painterResource(id = R.drawable.shopping),
                title = stringResource(id = R.string.digi_shopping),
                onClick = onBoxClick(navController,Constants.SHOPPING_URL)
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.giftcard),
                title = stringResource(id = R.string.gift_card),
                onClick = onBoxClick(navController,Constants.GIFT_CARD_URL)
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.digiplus),
                title = stringResource(id = R.string.digi_plus),
                onClick = onBoxClick(navController,Constants.DIGIPLUS_URL)
            )
            RoundedIconBox(
                image = painterResource(id = R.drawable.more),
                title = stringResource(id = R.string.more),
                bgColor = MaterialTheme.colors.grayCategory,
                onClick = onBoxClick(navController,Constants.MORE_URL)
            )
        }
    }
}

@Composable
fun onBoxClick(navController: NavController, url: String): () -> Unit = {
    navController.navigate(route = Screen.WebView.route + "?url=${url}")
}