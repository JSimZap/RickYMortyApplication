package com.example.rickmortyapplication.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIRickyMortyService {

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): RickyMortyResponse

    // Obtener detalles de un personaje
    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): Personaje
}