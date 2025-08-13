package com.example.projet.data.remote.dto

data class Article(
    val id: Int,
    val nom: String,
    val designation: String,
    val quantite: Int,
    val etat: String,
    val dateEntree: String
)

val articleList = listOf(
    Article(
        id = 1,
        nom = "TPE Verifone VX520",
        designation = "Terminal de paiement électronique",
        quantite = 30,
        etat = "Neuf",
        dateEntree = "2025-07-10"
    ),
    Article(
        id = 2,
        nom = "Licence Sage X3 ADV",
        designation = "Licence ERP avancée",
        quantite = 10,
        etat = "Actif",
        dateEntree = "2025-07-05"
    ),
    Article(
        id = 3,
        nom = "Cartes EMV vierges",
        designation = "Cartes à puce pour encodage bancaire",
        quantite = 2000,
        etat = "Stockée",
        dateEntree = "2025-06-28"
    ),
    Article(
        id = 4,
        nom = "Manuels utilisateur Sage X3",
        designation = "Documentation technique",
        quantite = 100,
        etat = "Imprimé",
        dateEntree = "2025-07-12"
    ),
    Article(
        id = 5,
        nom = "Câbles USB TPE",
        designation = "Accessoires pour terminaux de paiement",
        quantite = 150,
        etat = "Neuf",
        dateEntree = "2025-07-15"
    )
)
