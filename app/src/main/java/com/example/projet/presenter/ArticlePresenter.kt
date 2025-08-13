package com.example.projet.presenter

import com.example.projet.data.remote.dto.Article
import com.example.projet.data.remote.dto.articleList
import com.example.projet.ui.view.ArticleView

class ArticlePresenter(private val view: ArticleView) {

    fun loadArticles() {
        view.showLoading()

        // Ici on utilise directement les données statiques
        try {
            val articles: List<Article> = articleList
            view.hideLoading()
            view.showArticles(articles)
        } catch (e: Exception) {
            view.hideLoading()
            view.showError("Erreur lors du chargement des articles : ${e.message}")
        }
    }
}
