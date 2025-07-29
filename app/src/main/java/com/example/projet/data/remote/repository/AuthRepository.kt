package com.example.projet.data.repository

import com.example.projet.data.remote.api.ApiService
import com.example.projet.data.remote.dto.*
import retrofit2.Response

class AuthRepository(private val apiService: ApiService) {
    suspend fun login(username: String, password: String): Response<LoginResponse> {
        return apiService.login(LoginRequest(username, password))
    }
}