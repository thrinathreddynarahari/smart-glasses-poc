package com.ranesvision.app.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ranesvision.app.ui.album.AlbumScreen
import com.ranesvision.app.ui.dashboard.DashboardScreen
import com.ranesvision.app.ui.settings.SettingsScreen
import com.ranesvision.app.ui.splash.SplashScreen

object Routes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val ALBUM = "album"
    const val PROFILE = "profile"
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Routes.SPLASH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { fadeIn(tween(300)) },
        exitTransition = { fadeOut(tween(300)) }
    ) {
        composable(Routes.SPLASH) {
            SplashScreen(
                onSplashComplete = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(
            Routes.HOME,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(200)) }
        ) {
            DashboardScreen(
                onNavigateToAlbum = {
                    navController.navigate(Routes.ALBUM) {
                        popUpTo(Routes.HOME) { inclusive = false }
                    }
                }
            )
        }

        composable(
            Routes.ALBUM,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(200)) }
        ) {
            AlbumScreen()
        }

        composable(
            Routes.PROFILE,
            enterTransition = { fadeIn(tween(400)) },
            exitTransition = { fadeOut(tween(200)) }
        ) {
            SettingsScreen()
        }
    }
}
