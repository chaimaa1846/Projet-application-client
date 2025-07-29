package com.example.projet.data.repository

import com.example.projet.data.remote.api.ApiService
import com.example.projet.data.remote.dto.Facture
import retrofit2.Response

class FactureRepository(private val apiService: ApiService) {
    suspend fun getFactures(): Response<List<Facture>> {
        return apiService.getFactures()
    }

    suspend fun getFactureById(id: Int): Response<Facture> {
        return apiService.getFactureById(id)
    }

    suspend fun getFacturesByClient(clientId: Int): Response<List<Facture>> {
        return apiService.getFacturesByClient(clientId)
    }

    suspend fun createFacture(facture: Facture): Response<Facture> {
        return apiService.createFacture(facture)
    }
}