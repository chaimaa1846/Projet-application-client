package com.example.projet.data.remote.dto

data class LoginResponse(
    val success: Boolean,
    val token: String? = null,
    val message: String? = null,
    val user: User? = null
)