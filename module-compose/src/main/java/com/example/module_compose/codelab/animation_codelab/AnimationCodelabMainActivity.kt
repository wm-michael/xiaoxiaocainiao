package com.example.module_compose.codelab.animation_codelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.compose.AppTheme
import com.example.module_compose.codelab.animation_codelab.ui.theme.Home

class AnimationCodelabMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                Home()
            }
        }
    }
}