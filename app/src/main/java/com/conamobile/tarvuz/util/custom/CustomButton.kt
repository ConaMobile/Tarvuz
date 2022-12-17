package com.conamobile.tarvuz.util.custom

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginDefaultButton(
    text: String,
    click: () -> Unit,
    modifier: Modifier,
    enabled: Boolean = true,
) {
    Button(onClick = click,
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        enabled = enabled) {
        Text(text = text)
    }
}