package com.example.module_compose.codelab.animation_codelab.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.Green300
import com.example.compose.Green800
import com.example.compose.Yello100
import com.example.compose.Yello500

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
    TabRow(
        selectedTabIndex = tabPage.ordinal,
        containerColor = backgroundColor,
        indicator = { positions ->
            HomeTabIndicator(positions, tabPage)
        }) {
        HomeTabRow(icon = Icons.Default.Home, onClick = { onTabSeleted(TabPage.Home) })
        HomeTabRow(icon = Icons.Default.Email, onClick = { onTabSeleted(TabPage.Work) })
    }
}

@Composable
private fun HomeTabRow(icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable(onClick = onClick)
    ) {
        Icon(imageVector = icon, contentDescription = null)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "Home")
    }
}

@Composable
private fun HomeTabIndicator(tabPositions: List<TabPosition>, tabPage: TabPage) {

    val indicatorLeft = tabPositions[tabPage.ordinal].left
    val indicatorRight = tabPositions[tabPage.ordinal].right

    val color = if (tabPage == TabPage.Home) Yello500 else Green800

    Box(
        Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = indicatorLeft)
            .width(indicatorRight - indicatorLeft)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, color),
                RoundedCornerShape(4.dp)
            )
    )
}

@Preview()
@Composable
fun PrevieHome() {
    Home()
}