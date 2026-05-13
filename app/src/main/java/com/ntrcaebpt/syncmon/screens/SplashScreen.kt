package com.ntrcaebpt.syncmon.screens

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntrcaebpt.syncmon.R
import com.ntrcaebpt.syncmon.ui.theme.PurpleTintBG
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch



@Composable
fun SplashScreen(navController: NavController, context: Context=LocalContext.current, modifier: Modifier = Modifier) {
    val scale = remember { Animatable(1.5f) }
    val alpha = remember { Animatable(0f) }

    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val isLoggedIn = sharedPref.getBoolean("isLoggedIn", false)

    LaunchedEffect(key1 = true, key2 = true) {
        val alphaJob = launch {
            alpha.animateTo(1f, animationSpec = tween(3000))
        }
        val scaleJob = launch {
            scale.animateTo(2f, animationSpec = tween(3000))
        }

        alphaJob.join()
        scaleJob.join()

        delay(500L)
        if (isLoggedIn){
            navController.navigate("home_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        } else {
            navController.navigate("welcome_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }
    }
    Column(modifier= modifier.fillMaxSize().background(PurpleTintBG),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(R.drawable.logo_png), contentDescription = "Logo",
            modifier=modifier.width(184.dp).scale(scale.value).alpha(alpha.value))
    }
}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController(), LocalContext.current)
}