package com.example.rickmortyapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RickyMortyResponse(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val resultado: List<Episodio>
) {

    data class Info(
        @SerializedName("count") val count: Int,
        @SerializedName("pages") val pages: Int,
        @SerializedName("next") val next: String?,
        @SerializedName("prev") val prev: String?
    )
}

data class Episodio(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
): Serializable



data class Personaje(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String
)