package com.example.projet.data.remote.dto

data class Traitement(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: Double,
    val duree: String,
    val specialiste: String,
    val disponible: Boolean
)