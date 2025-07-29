package com.example.projet.ui.view

import com.example.projet.data.remote.dto.User

interface LoginView {
    fun showLoading()
    fun hideLoading()
    fun onLoginSuccess(token: String?, user: User?)
    fun onLoginError(message: String)
}