package com.example.projet.ui.view

import com.example.projet.data.remote.dto.Traitement

interface TraitementView {
    fun showLoading()
    fun hideLoading()
    fun showTraitements(traitements: List<Traitement>)
    fun showError(message: String)
}