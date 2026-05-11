package com.ntrcaebpt.syncmon.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ntrcaebpt.syncmon.ui.theme.PurpleTintBG
import androidx.core.content.edit
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ntrcaebpt.syncmon.view_models.ThingSpeakViewModel
import com.ntrcaebpt.syncmon.view_models.UiState

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val loginWith = sharedPref.getString("login_with", "thingspeak")

    if (loginWith == "thingspeak") {
        val apiKey = sharedPref.getString("thingspeak_api_key", "No Key Found")
        val channelId = sharedPref.getString("thingspeak_channel_id", "No ID Found")
        val viewModel: ThingSpeakViewModel = viewModel(factory = ThingSpeakViewModel.Factory)
        val state by viewModel.uiState.collectAsState()

        LaunchedEffect(key1 = channelId, key2 = apiKey) {
            if (channelId != null && apiKey != null && channelId != "No ID Found" && apiKey != "No Key Found") {
                viewModel.fetchFeeds(channelId, apiKey, 20)
            }
        }

        HomeScreenContent(
            loginWith = loginWith ?: "thingspeak",
            apiKey = apiKey ?: "No Key",
            channelId = channelId ?: "No ID",
            state = state,
            onLogout = {
                sharedPref.edit { clear() }
                navController.navigate("login_screen") {
                    popUpTo("home_screen") { inclusive = true }
                }
            },
            modifier = modifier
        )
    } else {
        Column(modifier.fillMaxSize().background(PurpleTintBG).padding(16.dp)) {
            Text("Logged in via: $loginWith", style = MaterialTheme.typography.bodyLarge)
            Text("Dashboard for this login method is not yet implemented.")
            Button(onClick = {
                sharedPref.edit { clear() }
                navController.navigate("login_screen") {
                    popUpTo("home_screen") { inclusive = true }
                }
            }) {
                Text("Logout")
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    loginWith: String,
    apiKey: String,
    channelId: String,
    state: UiState,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize().background(PurpleTintBG)) {
        Spacer(modifier.weight(0.01f))
        Text(
            text = "Dashboard",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier.weight(0.1f))
        Text(
            text = "Welcome back!",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Logged in via: $loginWith",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth(),
            color = Color.Gray
        )
        Text(
            text = "API Key: $apiKey",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 2.dp)
                .fillMaxWidth(),
            color = Color.Gray
        )
        Text(
            text = "Channel ID: $channelId",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 2.dp)
                .fillMaxWidth(),
            color = Color.Gray
        )
        Spacer(modifier.weight(0.01f))
        when (state) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            is UiState.Error -> Text("Error: ${state.message}", color = Color.Red, modifier = Modifier.padding(16.dp))
            is UiState.Success -> {
                val data = state.data
                Text(
                    text = "Channel Data: ${data.channel}",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp)
                )

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(data.feeds) { feed ->
                        Card(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 4.dp)) {
                            Text(text = "Value: ${feed ?: "N/A"}", modifier = Modifier.padding(8.dp))
                        }
                    }
                }
            }
        }
        Button(
            onClick = onLogout,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent(
        loginWith = "thingspeak",
        apiKey = "SAMPLE_KEY",
        channelId = "12345",
        state = UiState.Loading,
        onLogout = {}
    )
}
