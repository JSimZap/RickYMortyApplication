package com.example.rickmortyapplication.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interfaz que define los métodos de comunicacion con la API de Rick and Morty
 */

interface APIRickyMortyService {

    /**
     * Obtiene una lista de episodios paginados de la serie Rick and Morty
     * @param page Numero de la pagina a solicitar
     * @return Un objeto [RickyMortyResponse] con la informacion de los episodios
     */
    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): RickyMortyResponse


    /**
     * Obtiene los detalles de un personaje específico de la serie Rick and Morty.
     * @param characterId Identificador unico del personaje.
     * @return Un objeto [Personaje] con la informacion del personaje.
     */
    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") characterId: Int): Personaje
}