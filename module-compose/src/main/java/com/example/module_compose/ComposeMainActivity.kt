package com.example.module_compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.module_compose.codelab.animation_codelab.AnimationCodelabMainActivity

class ComposeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainHome()
        }
    }


}

@Composable
fun MainHome() {
    val mContext = LocalContext.current
    val titles = arrayOf("AnimationCodeLab", "b", "c")
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Text("basic animation", modifier = Modifier
            .background(color = Color.White)
            .padding(30.dp))
        LazyColumn(modifier = Modifier.fillMaxSize(), content = {
            items(titles) { title ->
                Button(onClick = {
                    mContext.startActivity(
                        Intent(
                            mContext,
                            AnimationCodelabMainActivity::class.java
                        )
                    )
                }) {
                    Text(text = title)
                }
            }
        })
    }
}

@Preview(showBackground = true)
@Composable
fun ComposePreview() {
    MainHome()
}
