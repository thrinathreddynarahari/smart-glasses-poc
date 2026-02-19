package com.ranesvision.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ranesvision.app.ui.navigation.BottomNavBar
import com.ranesvision.app.ui.navigation.NavGraph
import com.ranesvision.app.ui.navigation.Routes
import com.ranesvision.app.ui.theme.RanesVisionTheme
import com.ranesvision.app.ui.theme.DeepNavy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RanesVisionTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in listOf(
                    Routes.HOME,
                    Routes.ALBUM,
                    Routes.PROFILE
                )

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = DeepNavy,
                    bottomBar = {
                        if (showBottomBar) {
                            BottomNavBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        startDestination = Routes.SPLASH
                    )
                }
            }
        }
    }
}
