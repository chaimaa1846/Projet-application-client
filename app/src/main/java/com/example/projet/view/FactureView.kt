package com.example.projet.ui.view

import com.example.projet.data.remote.dto.Facture

interface FactureView {
    fun showLoading()
    fun hideLoading()
    fun showFactures(factures: List<Facture>)
    fun showError(message: String)
}