package com.example.projet.presenter

import com.example.projet.data.repository.FactureRepository
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.ui.view.FactureView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FacturePresenter(private val view: FactureView) {
    private val repository = FactureRepository(ApiClient.apiService)

    fun loadFactures() {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getFactures()
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view.showFactures(it)
                        }
                    } else {
                        view.showError("Erreur lors du chargement des factures")
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

    fun loadFacturesByClient(clientId: Int) {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getFacturesByClient(clientId)
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view.showFactures(it)
                        }
                    } else {
                        view.showError("Erreur lors du chargement des factures client")
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