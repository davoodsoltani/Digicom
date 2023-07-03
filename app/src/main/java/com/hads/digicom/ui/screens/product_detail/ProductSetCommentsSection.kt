package com.hads.digicom.ui.screens.product_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hads.digicom.R
import com.hads.digicom.data.model.product_detail.ProductDetail
import com.hads.digicom.navigation.Screen
import com.hads.digicom.ui.theme.*
import com.hads.digicom.utils.Constants

@Composable
fun ProductSetCommentsSection(
    navController: NavHostController,
    item: ProductDetail,
) {

    Column(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.samiLarge,
                vertical = MaterialTheme.spacing.medium
            )
            .clickable {
                if (Constants.USER_TOKEN == "null") {
                    navController.navigate(Screen.Profile.route)
                }else{
                    navController.navigate(Screen.NewComment.route + "?productId=${item._id}?productName=${item.name}?imageUrl=${item.imageSlider!![0].image}")
                }

            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                painter = painterResource(
                    id = R.drawable.comment
                ),
                contentDescription = "",
                Modifier.size(20.dp),
            )

            Text(
                text = stringResource(id = R.string.write_your_comment),
                Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                color = MaterialTheme.colors.darkText,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h5,
            )

            Icon(
                Icons.Outlined.KeyboardArrowLeft,
                contentDescription = "",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colors.settingArrow
            )
        }

        Text(
            text = stringResource(R.string.comment_desc),
            Modifier
                .padding(start = MaterialTheme.spacing.large + MaterialTheme.spacing.small),
            color = MaterialTheme.colors.semiDarkText,
            style = MaterialTheme.typography.h6,
        )

        Spacer(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.large,
                    top = MaterialTheme.spacing.medium,
                )
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colors.grayCategory)

        )

    }

}