package com.example.module_compose.codelab.animation_codelab.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.Green300
import com.example.compose.Yello100
import com.example.module_compose.R
import com.example.module_compose.codelab.animation_codelab.ui.Header
import com.example.module_compose.codelab.animation_codelab.ui.HomeTabBar
import com.example.module_compose.codelab.animation_codelab.ui.LoadingRow
import com.example.module_compose.codelab.animation_codelab.ui.TaskRow
import com.example.module_compose.codelab.animation_codelab.ui.TopicRow
import com.example.module_compose.codelab.animation_codelab.ui.WeatherRow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

enum class TabPage {
    Home,
    Work
}

@Composable
fun Home() {

    val allTopics = stringArrayResource(R.array.topics).toList()
    // Holds the topic that is currently expanded to show its body.
    var expandedTopic by remember { mutableStateOf<String?>(null) }

    val allTasks = stringArrayResource(R.array.tasks)
    // Holds all the tasks currently shown on the task list.
    val tasks = remember { mutableStateListOf(*allTasks) }

    // The currently selected tab.
    var tabPage by remember { mutableStateOf(TabPage.Home) }
    // True if the whether data is currently loading.
    var weatherLoading by remember { mutableStateOf(false) }
    // The background color. The value is changed by the current tab.
    val backgroundColor by animateColorAsState(if (tabPage == TabPage.Home) Yello100 else Green300)

    suspend fun loadWeather() {
        if (!weatherLoading) {
            weatherLoading = true
            delay(3000L)
            weatherLoading = false
        }
    }

    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier.padding(16.dp, 32.dp),
        containerColor = backgroundColor,
        topBar = {
            HomeTabBar(tabPage, onTabSeleted = { tabPage = it }, backgroundColor = backgroundColor)
        }) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item { Header(title = stringResource(id = R.string.weather)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    if (weatherLoading) {
                        LoadingRow()
                    } else {
                        WeatherRow(onRefresh = {
                            coroutineScope.launch {
                                loadWeather()
                            }
                        })
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            // Topics
            item { Header(title = stringResource(R.string.topics)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            items(allTopics) { topic ->
                TopicRow(topic = topic, expanded = expandedTopic == topic, onClick = {
                    expandedTopic = if (expandedTopic == topic) null else topic
                })
            }
            item { Spacer(modifier = Modifier.height(32.dp)) }
            item { Header(title = stringResource(R.string.tasks)) }
            item { Spacer(modifier = Modifier.height(16.dp)) }
            if (tasks.isEmpty()) {
                item {
                    TextButton(onClick = { tasks.clear(); tasks.addAll(allTasks) }) {
                        Text(stringResource(R.string.add_tasks))
                    }
                }
            } else {
                items(tasks) { task ->
                    key(task) {
                        TaskRow(task = task, onRemove = { tasks.remove(task) })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevieHome1() {
    Home()
}