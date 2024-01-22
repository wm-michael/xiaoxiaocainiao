package com.example.module_compose.codelab.animation_codelab.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.Green300
import com.example.compose.Yello100

private enum class TabPage {
    Home,
    Work
}

@Composable
fun Home() {

    // The currently selected tab.
    var tabPage by remember { mutableStateOf(TabPage.Home) }
    // The background color. The value is changed by the current tab.
    val backgroundColor by animateColorAsState(if (tabPage == TabPage.Home) Yello100 else Green300)

    Scaffold(
        modifier = Modifier.background(backgroundColor),
        topBar = {
            HomeTabBar(tabPage, onTabSeleted = { tabPage = it }, backgroundColor = backgroundColor)
        }) { padding ->
        Text(modifier = Modifier.padding(padding), text = "TEST")
    }
}

@Composable
private fun HomeTabBar(tabPage: TabPage, onTabSeleted: (TabPage) -> Unit, backgroundColor: Color) {
    Text(text = "HomeTabBar")
    TabRow(selectedTabIndex = tabPage.ordinal, containerColor = backgroundColor){
        HomeTab(icon = Icons.Default.Home, onClick = { onTabSeleted(TabPage.Home) })
        HomeTab(icon = Icons.Default.Email, onClick = { onTabSeleted(TabPage.Work) })
    }
}

@Composable
private fun HomeTab(icon: ImageVector, onClick: () -> Unit) {
    Row(modifier = Modifier.padding(16.dp).clickable(onClick = onClick)) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Home")
    }
}


@Preview()
@Composable
fun PrevieHome() {
    Home()
}