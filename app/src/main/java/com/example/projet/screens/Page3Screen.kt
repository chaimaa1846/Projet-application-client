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
import com.example.projet.data.remote.dto.factureList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen(navController: NavHostController) {
    val factures = factureList

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Liste des factures") },
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
            items(factures) { facture ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Facture n°${facture.id}", style = MaterialTheme.typography.titleMedium)
                        Text("Fournisseur : ${facture.nomFournisseur}")
                        Text("Date : ${facture.date}")
                        Text("Montant HT : ${facture.montantHT} MAD")
                        Text("Montant TTC : ${facture.montantTTC} MAD")
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Détail des lignes :", style = MaterialTheme.typography.titleSmall)
                        facture.lignes.forEach { ligne ->
                            Text("• ${ligne.designation} (${ligne.reference})")
                            Text("   Qté: ${ligne.quantite}, PU: ${ligne.prixUnitaire}, Total: ${ligne.totalLigne}")
                        }
                    }
                }
            }
        }
    }
}
