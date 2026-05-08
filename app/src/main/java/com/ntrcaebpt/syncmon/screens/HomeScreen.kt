package com.ntrcaebpt.syncmon.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier = Modifier){

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}