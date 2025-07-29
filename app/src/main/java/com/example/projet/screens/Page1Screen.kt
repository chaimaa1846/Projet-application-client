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
import com.example.projet.data.remote.dto.Traitement
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.data.repository.TraitementRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Page1Screen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val repository = remember { TraitementRepository(ApiClient.apiService) }

    var traitements by remember { mutableStateOf<List<Traitement>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val response = repository.getTraitements()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        traitements = response.body() ?: emptyList()
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
                        items(traitements) { traitement ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text("Nom : ${traitement.nom}", style = MaterialTheme.typography.titleMedium)
                                    Text("Description : ${traitement.description}")
                                    Text("Prix : ${traitement.prix} MAD")
                                    Text("Durée : ${traitement.duree}")
                                    Text("Spécialiste : ${traitement.specialiste}")
                                    Text("Disponible : ${if (traitement.disponible) "Oui" else "Non"}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
