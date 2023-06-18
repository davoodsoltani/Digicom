package com.hads.digicom.ui.screens.profile


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hads.digicom.ui.components.Loading3Dot
import com.hads.digicom.ui.theme.digicomRed
import com.hads.digicom.ui.theme.roundedShape
import com.hads.digicom.ui.theme.spacing

@Composable
fun LoadingButton() {
    Button(
        onClick = { },
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.digicomRed),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .padding(
                start = MaterialTheme.spacing.samiLarge,
                end = MaterialTheme.spacing.samiLarge,
                bottom = MaterialTheme.spacing.medium
            ),
        shape = MaterialTheme.roundedShape.small
    ) {

        Loading3Dot(isDark = false)
    }
}
