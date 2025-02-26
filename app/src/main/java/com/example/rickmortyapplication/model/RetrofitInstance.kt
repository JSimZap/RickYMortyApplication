package com.example.rickmortyapplication.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Objeto que proporciona una instancia de Retrofit configurada para comunicarse con la API de Rick and Morty.
 */
object RetrofitInstance {
    /**
     * URL base de la API de Rick and Morty.
     */
    private const val BASE_URL = "https://rickandmortyapi.com/api/"

    /**
     * Instancia de Retrofit configurada para comunicarse con la API de Rick and Morty.
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Interfaz que define los metodos de comunicacion con la API de Rick y Morty.
     */
    val api: APIRickyMortyService = retrofit.create(APIRickyMortyService::class.java)
}
