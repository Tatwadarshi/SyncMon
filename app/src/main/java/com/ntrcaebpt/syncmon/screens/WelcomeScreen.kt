 package com.ntrcaebpt.syncmon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntrcaebpt.syncmon.R
import com.ntrcaebpt.syncmon.ui.theme.Purple80
import com.ntrcaebpt.syncmon.ui.theme.PurpleTintBG

@Composable
fun WelcomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier
                .fillMaxSize()
                .background(PurpleTintBG)
        , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Box(
            modifier=modifier
                .fillMaxWidth()
                .weight(0.065f)
                .background(brush = Brush.verticalGradient(listOf(Purple80, PurpleTintBG)))
            , contentAlignment = Alignment.Center
        ){
            Box(modifier=modifier.size(300.dp).background(Color.Transparent, shape = CircleShape), contentAlignment = Alignment.Center)
            {
                Image(painter = painterResource(R.drawable.logo_png), contentDescription = "Img", modifier=modifier.size(184.dp))
            }
        }
        Column(modifier=modifier
            .fillMaxWidth()
            .weight(0.05f)
            .background(Color.Yellow)
            , horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Text("Welcome to SyncMon",
                fontSize = 60.sp,
                fontWeight = FontWeight(1000),
                color = Color(0xFF002FFF),
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.Center,
//                lineHeight = 28.sp,

            )
            Button(onClick = {
                                navController.navigate("login_screen"){
                                    popUpTo("welcome_screen") { inclusive = true }
                                }
                             },
                modifier=modifier.padding(16.dp),
                shape = CircleShape,
                colors = buttonColors(containerColor = Color(0xFF6D1DFF),
                    contentColor = Color.White)) {
                Text("Continue", fontSize = 24.sp, modifier = modifier.padding(8.dp))
            }

        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = rememberNavController())
}