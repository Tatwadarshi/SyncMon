package com.ntrcaebpt.syncmon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntrcaebpt.syncmon.R
import com.ntrcaebpt.syncmon.ui.theme.PurpleTintBG
@Composable
fun LoginScreen(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PurpleTintBG),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Heading at the top
        Text(
            text = "Login to Fetch Data",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF002FFF),
            modifier = Modifier.padding(top = 32.dp)
        )

        // App Logo at the top center
        Spacer(modifier = Modifier.weight(0.25f))

        Image(
            painter = painterResource(id = R.drawable.logo_png),
            contentDescription = "App Logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.weight(0.25f))

        // Login Options in a column in the center
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.navigate("appwrite_login_screen")},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D1DFF))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Login with ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.appwrite_logo),
                        contentDescription = "Appwrite Logo",
                        modifier = Modifier
                            .height(40.dp)
                            .width(130.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Text("Or")
            Button(
                onClick = { navController.navigate("thingspeak_login_screen") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D1DFF))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Using ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = R.drawable.thingspeak_logo),
                        contentDescription = "ThingSpeak Logo",
                        modifier = Modifier
                            .height(40.dp)
                            .width(130.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun AppwriteLoginScreen(navController: NavController, modifier: Modifier = Modifier){
    Column(modifier
        .fillMaxSize()
        .background(PurpleTintBG)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Spacer(modifier.weight(0.01f))
        Image(
            painter = painterResource(id = R.drawable.appwrite_logo),
            contentDescription = "Appwrite Logo",
            modifier = Modifier
                .padding(4.dp)
                .height(40.dp)
                .fillMaxWidth()
                .background(Color(0xFF19191C))
            ,
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier.weight(0.01f))

//        TO CHANGE From here:

        Text("Will be Available Very soon", fontSize = 24.sp)
        Spacer(modifier.weight(0.1f))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D1DFF))
        ){
            Text("Back To Login Menu", fontSize = 20.sp)
        }
        Spacer(modifier.weight(0.01f))
    }

}

@Composable
fun ThingSpeakLoginScreen(navController: NavController, context: Context, modifier: Modifier = Modifier){
    var thingSpeakReadAPIKey by remember { mutableStateOf("") }
    var thingSpeakChannelID by remember { mutableStateOf("") }

    var savedData by remember { mutableStateOf("") }

    Column(modifier
        .fillMaxWidth()
        .background(PurpleTintBG)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Spacer(modifier.weight(0.1f))
        Image(
            painter = painterResource(id = R.drawable.thingspeak_logo),
            contentDescription = "ThingSpeak Logo",
            modifier = Modifier
                .padding(4.dp)
                .height(40.dp)
                .fillMaxWidth()
                .background(Color(0xFF307EB2))
            ,
            contentScale = ContentScale.Fit,
        )
        Spacer(modifier.weight(0.01f))
        TextField(
            value = thingSpeakReadAPIKey,
            onValueChange = {thingSpeakReadAPIKey = it},
            label = {Text("Enter Your Read API Key")}
            )
        Spacer(modifier.weight(0.01f))
        TextField(
            value = thingSpeakChannelID,
            onValueChange = {thingSpeakChannelID = it},
            label = {Text("Enter Your Channel ID")}
            )
        Spacer(modifier.weight(0.01f))
        Button(
            onClick = {
                val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
                sharedPref.edit {
                    putString("thingspeak_api_key", thingSpeakReadAPIKey)
                    putString("thingspeak_channel_id", thingSpeakChannelID)
                    putBoolean("isLoggedIn", true)
                    putString("login_with", "thingspeak")
                }
                navController.navigate("home_screen") {
                    popUpTo("login_screen") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D1DFF)),
            enabled = if (thingSpeakChannelID=="" || thingSpeakReadAPIKey == "") false else true
        ){
            Text("Save & Connect", fontSize = 20.sp)
        }

        Spacer(modifier.weight(0.1f))
        Text("Or", fontSize = 20.sp)
        Spacer(modifier.weight(0.1f))

        Button(
            onClick = {
                val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
                sharedPref.edit {
                    putBoolean("isLoggedIn", true)
                    putString("login_with", "thingspeak_web")
                }
                navController.navigate("home_screen") {
                    popUpTo("login_screen") { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6D1DFF)),
            enabled = if (thingSpeakChannelID=="" || thingSpeakReadAPIKey == "") true else false
        ){
            Text("Go to Web View", fontSize = 20.sp)
        }

        Spacer(modifier.weight(0.5f))

    }
}

@Preview(showSystemUi = true)
@Composable
fun LogInScreenPreview() {
    ThingSpeakLoginScreen(navController = rememberNavController(), LocalContext.current)
//    AppwriteLoginScreen(navController = rememberNavController())
//    LoginScreen(navController = rememberNavController())
}
