package com.example.rickmortyapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Esta clase representa la respuesta que proporciona  la API de Rick y Morty
 * @property info Información de la paginación de la API
 * @property resultado Lista de episodios obtenidos de la API
 */
data class RickyMortyResponse(
    @SerializedName("info") val info: Info,
    @SerializedName("results") val resultado: List<Episodio>
)

/**
 * Contiene la información sobre la paginación de los resultados de la API
 * @property count Numero total de resultados disponibles
 * @property pages Numero total de paginas disponibles
 * @property next URL de la siguiente pagina de resultados, si existe
 * @property prev URL de la página anterior de resultados, si existe
 */
data class Info(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("prev") val prev: String?
)


/**
 * Representa un episodio de la serie Rick and Morty
 * @property id Identificador unico del episodio
 * @property name Nombre del episodio
 * @property airDate Fecha de emision del episodio
 * @property episode Codigo del episodio en el formato "SXXEXX"
 * @property characters Lista de URLs de los personajes que aparecen en el episodio
 * @property url URL del episodio en la API
 * @property created Fecha y hora en que el episodio fue agregado a la base de datos
 */
data class Episodio(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode") val episode: String,
    @SerializedName("characters") val characters: List<String>,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
): Serializable



/**
 * Representa un personaje de la serie Rick and Morty
 * @property id Identificador unico del personaje
 * @property name Nombre del personaje
 * @property image URL de la imagen del personaje
 * @property status Estado actual del perosnaje (vivo, muerto o desconocido)
 * @property species Especie a la que pertenece el personaje
 * @property gender Genero del personaje
 */
data class Personaje(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("status") val status: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String
):Serializable