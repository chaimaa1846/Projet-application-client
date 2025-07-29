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
import com.example.projet.data.remote.dto.Facture
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.data.repository.FactureRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page3Screen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val repository = remember { FactureRepository(ApiClient.apiService) }

    var factures by remember { mutableStateOf<List<Facture>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val response = repository.getFactures()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        factures = response.body() ?: emptyList()
                    } else {
                        errorMessage = "Erreur serveur"
                    }
                    isLoading = false
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    errorMessage = "Erreur réseau : ${e.message}"
                    isLoading = false
                }
            }
        }
    }

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    Text(
                        text = errorMessage ?: "",
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
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
                                    Text("N° Facture : ${facture.numeroFacture}", style = MaterialTheme.typography.titleMedium)
                                    Text("Client : ${facture.clientNom} (ID: ${facture.clientId})")
                                    Text("Date de création : ${facture.dateCreation}")
                                    Text("Montant total : ${facture.montantTotal} MAD")
                                    Text("Statut : ${facture.statut}")
                                    Text("Nombre d'items : ${facture.items.size}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
