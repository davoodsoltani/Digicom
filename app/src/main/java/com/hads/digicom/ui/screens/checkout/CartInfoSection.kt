package com.hads.digicom.ui.screens.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hads.digicom.ui.theme.grayCategory
import com.hads.digicom.ui.theme.spacing
import com.hads.digicom.R
@Composable
fun CartInfoSection() {
    Spacer(modifier = Modifier.height(MaterialTheme.spacing.samiLarge))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.grayCategory)
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            modifier = Modifier
                .weight(0.1f)
                .padding(
                    horizontal = MaterialTheme.spacing.small
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            Icon(
                painter = painterResource(id = R.drawable.info),
                contentDescription = "",
                modifier = Modifier.size(26.dp),
                tint = Color.DarkGray
            )
        }

        Column(
            modifier = Modifier
                .weight(0.9f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ){
            Text(
                modifier = Modifier,
                text = stringResource(id = R.string.factor_info),
                textAlign = TextAlign.Start,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6,
            )
        }

    }
}