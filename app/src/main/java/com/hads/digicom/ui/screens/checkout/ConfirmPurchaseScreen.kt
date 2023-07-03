package com.hads.digicom.ui.screens.checkout

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.hads.digicom.R
import com.hads.digicom.data.model.checkout.ConfirmPurchase
import com.hads.digicom.navigation.Screen
import com.hads.digicom.ui.theme.digicomRed
import com.hads.digicom.ui.theme.roundedShape
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.utils.Constants
import com.hads.digicom.utils.DigitHelper
import com.hads.digicom.utils.ZarinPurchase
import com.hads.digicom.viewmodel.BasketViewModel
import com.hads.digicom.viewmodel.CheckoutViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ConfirmPurchaseScreen(
    navController: NavController,
    orderId: String,
    orderPrice: String,
    basketViewModel: BasketViewModel = hiltViewModel(),
    checkoutViewModel: CheckoutViewModel = hiltViewModel()
) {


    val context = LocalContext.current
    val activity = context as Activity

    var orderState by remember { mutableStateOf(context.getString(R.string.waiting_for_purchase)) }

    LaunchedEffect(true ){
        ZarinPurchase.fakePurchase(
            activity,
            1000,//orderPrice.toLong(),
            context.getString(R.string.test_purchase)
        ) { transactionID ->
            orderState = context.getString(R.string.purchase_is_ok)
            basketViewModel.deleteAllItems()
            checkoutViewModel.confirmPurchase(
                ConfirmPurchase(
                    token = Constants.USER_TOKEN,
                    transactionId = transactionID,
                    orderId = orderId
                )
            )
            Log.e("3636", "Transaction ID : $transactionID")
        }
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                MaterialTheme.spacing.medium,
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.final_price),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = DigitHelper.digitByLocateAndSeparator(orderPrice),
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_status),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderState,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.order_code),
                style = MaterialTheme.typography.h5
            )

            Text(
                text = orderId,
                style = MaterialTheme.typography.h5
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Button(
            onClick = {
                navController.navigate(Screen.Home.route){
                    popUpTo(Screen.Home.route) {
                        inclusive = true
                    }
                }
            },
            border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.digicomRed),
            shape = MaterialTheme.roundedShape.small,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        MaterialTheme.spacing.small,
                    ),
                text = stringResource(id = R.string.return_to_home_page),
                color = MaterialTheme.colors.digicomRed,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
            )
        }

    }


}