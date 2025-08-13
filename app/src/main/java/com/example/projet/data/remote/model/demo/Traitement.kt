package com.example.projet.data.remote.dto

data class Traitement(
    val id: Int,
    val nomProduit: String,
    val idLivraison: String,
    val nomFournisseur: String,
    val idFournisseur: String,
    val quantite: Int,
    val dateBL: String
)

val traitementList = listOf(
    Traitement(
        id = 1,
        nomProduit = "Carte bancaire EMV",
        idLivraison = "LIV20250801",
        nomFournisseur = "Gemalto",
        idFournisseur = "FRN001",
        quantite = 1000,
        dateBL = "2025-08-01"
    ),
    Traitement(
        id = 2,
        nomProduit = "Terminal Verifone VX520",
        idLivraison = "LIV20250715",
        nomFournisseur = "Verifone France",
        idFournisseur = "FRN002",
        quantite = 50,
        dateBL = "2025-07-15"
    ),
    Traitement(
        id = 3,
        nomProduit = "Licence Sage X3",
        idLivraison = "LIV20250720",
        nomFournisseur = "Sage Maroc",
        idFournisseur = "FRN003",
        quantite = 5,
        dateBL = "2025-07-20"
    ),
    Traitement(
        id = 4,
        nomProduit = "Support technique Sage X3",
        idLivraison = "LIV20250725",
        nomFournisseur = "Sage Maroc",
        idFournisseur = "FRN003",
        quantite = 1,
        dateBL = "2025-07-25"
    ),
    Traitement(
        id = 5,
        nomProduit = "Mise à jour logiciel TPE",
        idLivraison = "LIV20250805",
        nomFournisseur = "Verifone France",
        idFournisseur = "FRN002",
        quantite = 20,
        dateBL = "2025-08-05"
    )
)
