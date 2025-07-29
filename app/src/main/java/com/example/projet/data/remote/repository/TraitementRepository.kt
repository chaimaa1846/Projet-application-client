package com.example.projet.data.repository

import com.example.projet.data.remote.api.ApiService
import com.example.projet.data.remote.dto.Traitement
import retrofit2.Response

class TraitementRepository(private val apiService: ApiService) {
    suspend fun getTraitements(): Response<List<Traitement>> {
        return apiService.getTraitements()
    }

    suspend fun getTraitementById(id: Int): Response<Traitement> {
        return apiService.getTraitementById(id)
    }
}