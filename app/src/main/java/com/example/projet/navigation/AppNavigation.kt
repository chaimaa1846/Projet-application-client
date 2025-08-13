package com.example.projet.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projet.screens.DashboardScreen
import com.example.projet.screens.LoginScreen
import com.example.projet.screens.Page1Screen
import com.example.projet.screens.Page2Screen
import com.example.projet.screens.Page3Screen

import com.example.projet.screens.AboutScreen
import com.example.projet.screens.ForgotPasswordScreen
import com.example.projet.screens.ProfileScreen


@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("dashboard") {
            DashboardScreen(navController)
        }
        // ✅ Ajout des 4 nouvelles pages
        composable("page1") {
            Page1Screen(navController)
        }
        composable("page2") {
            Page2Screen(navController)
        }
        composable("page3") {
            Page3Screen(navController)
        }

        composable("forgotPassword") {
           ForgotPasswordScreen(navController)
        }


        composable("profile") {
            ProfileScreen(navController)
        }

        composable("about") {
            AboutScreen(navController)
        }

    }
}
