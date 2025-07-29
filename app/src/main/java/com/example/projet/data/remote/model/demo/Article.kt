package com.example.projet.data.remote.dto

data class Article(
    val id: Int,
    val nom: String,
    val description: String,
    val prix: Double,
    val stock: Int,
    val categorie: String,
    val imageUrl: String? = null,
    val disponible: Boolean
)