package com.example.projet.data.repository

import com.example.projet.data.remote.api.ApiService
import com.example.projet.data.remote.dto.Article
import retrofit2.Response

class ArticleRepository(private val apiService: ApiService) {
    suspend fun getArticles(): Response<List<Article>> {
        return apiService.getArticles()
    }

    suspend fun getArticleById(id: Int): Response<Article> {
        return apiService.getArticleById(id)
    }

    suspend fun getArticlesByCategorie(categorie: String): Response<List<Article>> {
        return apiService.getArticlesByCategorie(categorie)
    }
}