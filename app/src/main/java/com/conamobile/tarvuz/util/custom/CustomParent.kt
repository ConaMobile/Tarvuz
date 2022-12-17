package com.conamobile.tarvuz.util.custom

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ParentView(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(modifier = modifier
            .fillMaxSize()) {
            content()
        }
    }
}