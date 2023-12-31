package com.hads.digicom.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hads.digicom.R
import com.hads.digicom.ui.components.IconWithRotate
import com.hads.digicom.ui.theme.darkText
import com.hads.digicom.ui.theme.digicomLightRed
import com.hads.digicom.ui.theme.localShape
import com.hads.digicom.ui.theme.localSpacing

@Composable
fun AmazingShowMorItem() {
    Card(
        modifier = Modifier
            .size(180.dp, 375.dp)
            .padding(
                end = localSpacing.current.medium,
                start = localSpacing.current.semiSmall,
                top = localSpacing.current.samiLarge
            ),
        shape = localShape.current.small,
        contentColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            IconWithRotate(
                painter = painterResource(id = R.drawable.show_more),
                tint = MaterialTheme.colors.digicomLightRed
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.show_all),
                style = MaterialTheme.typography.h6,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colors.darkText
            )

        }
    }
}