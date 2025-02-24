package com.example.rickmortyapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyapplication.model.Episodio
import com.example.rickmortyapplication.model.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EpisodioDetalleViewModel : ViewModel() {

    private var _episode: Episodio? = null
    val episode: Episodio? get() = _episode
    private var _characterImages: List<String> = emptyList()
    val characterImages: List<String> get() = _characterImages


    suspend fun loadEpisode(episodeId: Int) {
        withContext(Dispatchers.IO) {
            try {

                val response = RetrofitInstance.api.getEpisodeByUrl("episode/$episodeId")
                if (response.isSuccessful) {
                    _episode = response.body()?.resultado?.firstOrNull()


                    _episode?.characters?.let { characters ->
                        _characterImages = characters
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}