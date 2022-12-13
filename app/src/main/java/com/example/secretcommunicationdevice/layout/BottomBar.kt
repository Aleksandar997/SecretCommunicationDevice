package com.example.secretcommunicationdevice.layout

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Quickreply
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.secretcommunicationdevice.components.communicate.Communicate
import com.example.secretcommunicationdevice.components.peers.Peers
import com.example.secretcommunicationdevice.components.templates.Templates
import com.example.secretcommunicationdevice.services.static.ReactiveService

private class BottomNavItem(var title:String, var icon: ImageVector, var screen_route:String, var view_composable: @Composable () -> Unit, var is_start_destination: Boolean = false)

private val items = listOf(
    BottomNavItem("Templates", Icons.Default.LibraryBooks,"templates", Templates),
    BottomNavItem("Communicate", Icons.Default.Quickreply,"communicate", Communicate, true),
    BottomNavItem("Peers", Icons.Default.AccountTree,"peers", Peers ),
    BottomNavItem("History", Icons.Default.History,"history", Peers)
)

@Composable
fun BottomNavigation(navController: NavController) {
    BottomAppBar(
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(imageVector = item.icon, item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp
                    )
                },
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    ReactiveService.activeRoute.value = item.screen_route
                    ReactiveService.formTitle.value = item.title
                    navController.navigate(item.screen_route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {
    val startDestination = items.find { it.is_start_destination }
    NavHost(navController, startDestination = startDestination?.screen_route.toString()) {
        ReactiveService.formTitle.value = startDestination?.title.toString()

        for(item in items) {
            composable(item.screen_route) {
                item.view_composable()
            }
        }
    }
}
