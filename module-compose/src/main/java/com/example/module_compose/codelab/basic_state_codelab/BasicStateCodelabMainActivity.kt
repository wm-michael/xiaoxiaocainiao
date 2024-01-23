package com.example.module_compose.codelab.basic_state_codelab

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme

class BasicStateCodelabMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp(wellnessViewModel: WellnessViewModel = viewModel()) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column {
            StatefulCounter()
            WellnessTasksList(
                wellnessViewModel.tasks,
                onCheckedTask = { task, checked ->
                    wellnessViewModel.changeTaskChecked(
                        task,
                        checked
                    )
                },
                onCloseTask = { task -> wellnessViewModel.remove(task) })
        }
    }
}


@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview2() {
    AppTheme {
        MainApp()
    }
}