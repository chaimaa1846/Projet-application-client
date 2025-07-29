package com.example.projet.presenter

import com.example.projet.data.repository.ArticleRepository
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.ui.view.ArticleView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArticlePresenter(private val view: ArticleView) {
    private val repository = ArticleRepository(ApiClient.apiService)

    fun loadArticles() {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getArticles()
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (response.isSuccessful) {
                        response.body()?.let {
                            view.showArticles(it)
                        }
                    } else {
                        view.showError("Erreur lors du chargement des articles")
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