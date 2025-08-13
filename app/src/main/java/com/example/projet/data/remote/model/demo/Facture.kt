package com.example.projet.data.remote.dto

data class Facture(
    val id: Int,
    val nomFournisseur: String,
    val date: String,
    val montantHT: Double,
    val montantTTC: Double,
    val lignes: List<LigneFacture>
)

data class LigneFacture(
    val reference: String,
    val designation: String,
    val quantite: Int,
    val prixUnitaire: Double,
    val totalLigne: Double
)

val factureList = listOf(
    Facture(
        id = 1,
        nomFournisseur = "Gemalto",
        date = "2025-08-01",
        montantHT = 8000.0,
        montantTTC = 8800.0,
        lignes = listOf(
            LigneFacture("CARTE-EMV", "Cartes EMV vierges", 1000, 8.0, 8000.0)
        )
    ),
    Facture(
        id = 2,
        nomFournisseur = "Verifone France",
        date = "2025-07-15",
        montantHT = 90000.0,
        montantTTC = 99000.0,
        lignes = listOf(
            LigneFacture("TPE-VX520", "Terminal Verifone VX520", 50, 1800.0, 90000.0)
        )
    ),
    Facture(
        id = 3,
        nomFournisseur = "Sage Maroc",
        date = "2025-07-20",
        montantHT = 12500.0,
        montantTTC = 13750.0,
        lignes = listOf(
            LigneFacture("SAGEX3-ADV", "Licence Sage X3 avancée", 5, 2500.0, 12500.0)
        )
    ),
    Facture(
        id = 4,
        nomFournisseur = "Verifone France",
        date = "2025-08-05",
        montantHT = 36000.0,
        montantTTC = 39600.0,
        lignes = listOf(
            LigneFacture("CABLE-USB", "Câbles USB TPE", 150, 240.0, 36000.0)
        )
    ),
    Facture(
        id = 5,
        nomFournisseur = "Sage Maroc",
        date = "2025-08-10",
        montantHT = 2000.0,
        montantTTC = 2200.0,
        lignes = listOf(
            LigneFacture("MANUEL-2025", "Manuels utilisateur Sage X3", 100, 20.0, 2000.0)
        )
    )
)
