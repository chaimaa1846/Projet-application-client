package com.example.projet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projet.navigation.AppNavigation
import com.example.projet.ui.theme.ProjetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjetTheme {
                AppNavigation()
            }
        }
    }
}
