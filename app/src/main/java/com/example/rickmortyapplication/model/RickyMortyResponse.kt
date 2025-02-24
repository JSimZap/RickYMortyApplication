package com.example.rickmortyapplication.model

import com.google.gson.annotations.SerializedName

data class RickyMortyResponse(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val resultado: List<Episodio>
) {
    // Clase interna para la información de las demas paginas
    data class Info(
        @SerializedName("count") val count: Int,
        @SerializedName("pages") val pages: Int,
        @SerializedName("next") val next: String?,
        @SerializedName("prev") val prev: String?
    )
}