//package com.example.rickmortyapplication.viewModel
//
//import androidx.lifecycle.ViewModel
//import com.example.rickmortyapplication.model.Episodio
//import com.example.rickmortyapplication.model.RetrofitInstance
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//
//class EpisodiosViewModel : ViewModel() {
//
//    private val _episodes = mutableListOf<Episodio>()
//    private var nextPageUrl: String? = null
//
//
//    fun getEpisodes(): List<Episodio> = _episodes
//
//
//    suspend fun loadFirstPage() {
//        withContext(Dispatchers.IO) {
//            try {
//                val response = RetrofitInstance.api.getEpisode()
//                if (response.isSuccessful) {
//                    val rickyMortyResponse = response.body()
//                    if (rickyMortyResponse != null) {
//                        // Agregar los episodios a la lista
//                        _episodes.addAll(rickyMortyResponse.resultado)
//                        // Guardar la URL de la siguiente página
//                        nextPageUrl = rickyMortyResponse.info.next
//                    }
//                }
//            } catch (e: Exception) {
//                //e.printStackTrace()
//            }
//        }
//    }
//
//
//    suspend fun loadNextPage() {
//        withContext(Dispatchers.IO) {
//            try {
//                // Verificar si hay una siguiente página
//                if (nextPageUrl != null) {
//                    val response = RetrofitInstance.api.getEpisodeByUrl(nextPageUrl!!)
//                    if (response.isSuccessful) {
//                        val rickyMortyResponse = response.body()
//                        if (rickyMortyResponse != null) {
//                            // Agregar los episodios a la lista
//                            _episodes.addAll(rickyMortyResponse.resultado)
//                            // Actualizar la URL de la siguiente página
//                            nextPageUrl = rickyMortyResponse.info.next
//                        }
//                    }
//                }
//            } catch (e: Exception) {
//                //e.printStackTrace()
//            }
//        }
//    }
//}