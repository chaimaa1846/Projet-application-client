package com.example.projet.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projet.data.remote.dto.traitementList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1Screen(navController: NavHostController) {
    val traitements = traitementList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Liste des traitements") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour",
                            tint = Color(0xFF6D4C41)
                        )
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            items(traitements) { traitement ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nom produit : ${traitement.nomProduit}", style = MaterialTheme.typography.titleMedium)
                        Text("ID Livraison : ${traitement.idLivraison}")
                        Text("Nom Fournisseur : ${traitement.nomFournisseur}")
                        Text("ID Fournisseur : ${traitement.idFournisseur}")
                        Text("Quantité : ${traitement.quantite}")
                        Text("Date BL : ${traitement.dateBL}")
                    }
                }
            }
        }
    }
}
