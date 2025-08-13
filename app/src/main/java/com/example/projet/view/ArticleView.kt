package com.example.projet.ui.view

import com.example.projet.data.remote.dto.Article

interface ArticleView {
    fun showLoading()
    fun hideLoading()
    fun showArticles(articles: List<Article>)
    fun showError(message: String)
}
