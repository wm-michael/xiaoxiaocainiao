package com.example.module_compose.codelab.basic_state_codelab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatefulCounter() {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ })
}


@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've pressed the button $count times")
        }
        Button(onClick = onIncrement, modifier = Modifier.padding(top = 16.dp), enabled = count < 10) {
            Text(text = "Add one")
        }
    }
}