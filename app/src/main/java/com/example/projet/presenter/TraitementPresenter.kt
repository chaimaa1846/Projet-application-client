package com.example.projet.presenter

import com.example.projet.data.repository.TraitementRepository
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.ui.view.TraitementView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TraitementPresenter(private val view: TraitementView) {
    private val repository = TraitementRepository(ApiClient.apiService)

    fun loadTraitements() {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getTraitements()
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view.showTraitements(it)
                        }
                    } else {
                        view.showError("Erreur lors du chargement des traitements")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    view.showError("Erreur réseau: ${e.message}")
                }
            }
        }
    }
}