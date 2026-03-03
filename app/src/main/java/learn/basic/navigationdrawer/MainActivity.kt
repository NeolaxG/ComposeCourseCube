package learn.basic.navigationdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import learn.basic.navigationdrawer.navigation.DrawerScreenData
import learn.basic.navigationdrawer.screens.AboutScreen
import learn.basic.navigationdrawer.screens.ContactScreen
import learn.basic.navigationdrawer.screens.HomeScreen
import learn.basic.navigationdrawer.ui.theme.NavigationDrawerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screens = listOf(
                DrawerScreenData.Home,
                DrawerScreenData.Contact,
                DrawerScreenData.About
            )
            val selectedItem = remember { mutableStateOf(screens[0]) }
            val drawerState = rememberDrawerState(DrawerValue.Closed)
            val scope = rememberCoroutineScope()
            ModalNavigationDrawer(
                drawerState = drawerState,
                modifier = Modifier.systemBarsPadding(),
                drawerContent = {
                    ModalDrawerSheet(
                        drawerContainerColor = Color.DarkGray,
                        drawerContentColor =  Color.LightGray
                    ) {
                        screens.forEach { item ->
                            NavigationDrawerItem(
                                label= { Text(item.title, fontSize = 22.sp) },
                                selected = selectedItem.value==item,
                                onClick = {
                                    scope.launch { drawerState.close() }
                                    selectedItem.value = item
                                },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color.Transparent,
                                    unselectedContainerColor = Color.Transparent,
                                    selectedTextColor = Color.White,
                                    unselectedTextColor = Color.LightGray
                                )
                            )
                        }
                    }
                },
                content={
                    Row{
                        IconButton(onClick = {scope.launch {drawerState.open()}},
                            content = {
                                Icon(
                                    Icons.Filled.Menu,
                                    "Меню"
                                )
                            }
                        )
                        Text(selectedItem.value.title, fontSize = 28.sp)
                    }
                    when (val currentScreen = selectedItem.value) {
                        is DrawerScreenData.Home -> HomeScreen()
                        is DrawerScreenData.Contact -> ContactScreen()
                        is DrawerScreenData.About -> AboutScreen()
                    }
                }
            )
        }
    }
}