package com.example.projet.data.remote.api

import com.example.projet.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("traitements")
    suspend fun getTraitements(): Response<List<Traitement>>

    @GET("traitements/{id}")
    suspend fun getTraitementById(@Path("id") id: Int): Response<Traitement>

    @GET("articles")
    suspend fun getArticles(): Response<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") id: Int): Response<Article>

    @GET("articles/categorie/{categorie}")
    suspend fun getArticlesByCategorie(@Path("categorie") categorie: String): Response<List<Article>>

    @GET("factures")
    suspend fun getFactures(): Response<List<Facture>>

    @GET("factures/{id}")
    suspend fun getFactureById(@Path("id") id: Int): Response<Facture>

    @GET("factures/client/{clientId}")
    suspend fun getFacturesByClient(@Path("clientId") clientId: Int): Response<List<Facture>>

    @POST("factures")
    suspend fun createFacture(@Body facture: Facture): Response<Facture>
}