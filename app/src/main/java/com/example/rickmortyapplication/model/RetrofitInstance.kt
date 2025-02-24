package com.example.rickmortyapplication.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"


    //Utilizamos by lazy para que la variable "api" se inicialice solo cuando se la utilice por primera vez
    val api: APIRickyMortyService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIRickyMortyService::class.java)
    }
}