package com.example.projet.data.remote.dto

data class Facture(
    val id: Int,
    val numeroFacture: String,
    val clientId: Int,
    val clientNom: String,
    val dateCreation: String,
    val montantTotal: Double,
    val statut: String,
    val items: List<FactureItem>
)