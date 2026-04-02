package com.ntrcaebpt.syncmon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ntrcaebpt.syncmon.screens.SplashScreen
import com.ntrcaebpt.syncmon.screens.WelcomeScreen

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController)
        }
        composable("welcome_screen"){
            WelcomeScreen(navController)
        }
        composable("home_screen") {
//            HomeScreen()
        }
    }
}