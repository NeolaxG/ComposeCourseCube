package learn.basic.navigationdrawer.navigation

import androidx.compose.runtime.Composable
import learn.basic.navigationdrawer.screens.AboutScreen
import learn.basic.navigationdrawer.screens.ContactScreen
import learn.basic.navigationdrawer.screens.HomeScreen

sealed class DrawerScreenData(val title: String, val content: @Composable () -> Unit) {
    object Home : DrawerScreenData("Home", { HomeScreen() })
    object Contact : DrawerScreenData("Contact", { ContactScreen() })
    object About : DrawerScreenData("About", { AboutScreen() })
}