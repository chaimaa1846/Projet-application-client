package com.example.projet.data.remote.dto

data class FactureItem(
    val id: Int,
    val type: String, // "traitement" ou "article"
    val itemId: Int,
    val nom: String,
    val quantite: Int,
    val prixUnitaire: Double,
    val sousTotal: Double
)