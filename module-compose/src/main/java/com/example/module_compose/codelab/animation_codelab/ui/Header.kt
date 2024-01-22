package com.example.module_compose.codelab.animation_codelab.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Header(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium
    )
}