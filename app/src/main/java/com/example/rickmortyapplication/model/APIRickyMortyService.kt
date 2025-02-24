package com.example.rickmortyapplication.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url


interface APIRickyMortyService {

    @GET("episode")
    suspend fun getEpisode(): Response<RickyMortyResponse>

    @GET
    suspend fun getEpisodeByUrl(@Url url: String): Response<RickyMortyResponse>
}