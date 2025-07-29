package com.example.projet.presenter

import com.example.projet.data.repository.AuthRepository
import com.example.projet.data.remote.network.ApiClient
import com.example.projet.ui.view.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginPresenter(private val view: LoginView) {
    private val repository = AuthRepository(ApiClient.apiService)

    fun login(username: String, password: String) {
        view.showLoading()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.login(username, password)
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    if (response.isSuccessful && response.body()?.success == true) {
                        view.onLoginSuccess(response.body()?.token, response.body()?.user)
                    } else {
                        view.onLoginError(response.body()?.message ?: "Erreur de connexion")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    view.hideLoading()
                    view.onLoginError("Erreur réseau: ${e.message}")
                }
            }
        }
    }
}