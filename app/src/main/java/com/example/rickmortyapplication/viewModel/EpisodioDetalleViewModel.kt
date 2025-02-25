//package com.example.rickmortyapplication.viewModel
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.rickmortyapplication.model.Episodio
//import com.example.rickmortyapplication.model.Personaje
//import com.example.rickmortyapplication.model.RetrofitInstance
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class EpisodioDetalleViewModel : ViewModel() {
//
//    private var _episode: Episodio? = null
//    val episode: Episodio? get() = _episode
//
//    private var _characterImages: List<String> = emptyList()
//    val characterImages: List<String> get() = _characterImages
//
//    suspend fun loadEpisode(episodeId: Int) {
//        withContext(Dispatchers.IO) {
//            try {
//                val response = RetrofitInstance.api.getEpisodeByUrl("episode/$episodeId")
//                if (response.isSuccessful) {
//                    _episode = response.body()?.resultado?.firstOrNull()
//                    _episode?.characters?.let { characters ->
//                        _characterImages = characters.map { it } // Aquí debes obtener las URLs de las imágenes
//                    }
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
//
//    suspend fun getCharacterDetails(url: String): Personaje {
//        return withContext(Dispatchers.IO) {
//            val response = RetrofitInstance.api.getPersonajeByURL(url)
//            if (response.isSuccessful) {
//                val character = response.body()
//                Personaje(
//                    name = character?.name ?: "Unknown",
//                    status = character?.status ?: "Unknown",
//                    gender = character?.gender ?: "Unknown",
//                    imageUrl = character?.imageUrl ?: ""
//                )
//            } else {
//                Personaje("Unknown", "Unknown", "Unknown", "Unknown")
//            }
//        }
//    }
//}
