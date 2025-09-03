package com.example.mychatapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {


    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.AuthScreen
    ){
        composable<Routes.AuthScreen>{


        }

        composable<Routes.MainScreen>{

        }
        composable<Routes.ChatScreen>{

        }

        composable<Routes.HomeScreen>{

        }

    }

}