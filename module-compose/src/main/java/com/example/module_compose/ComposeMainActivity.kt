package com.example.module_compose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class ComposeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            Text("Hello world!", modifier = Modifier.background(color = Color.White))
        }
    }



}

@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    Text("Hello world!", modifier = Modifier.background(color = Color.White))
}
