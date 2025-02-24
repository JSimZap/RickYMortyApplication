package com.example.rickmortyapplication.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickmortyapplication.databinding.ActivityEpisodioDetalleBinding
import com.example.rickmortyapplication.model.Personaje
import com.example.rickmortyapplication.viewModel.EpisodioDetalleViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EpisodioDetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEpisodioDetalleBinding
    private val viewModel: EpisodioDetalleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodioDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val episodeId = intent.getIntExtra("EPISODE_ID", -1)
        if (episodeId != -1) {

            CoroutineScope(Dispatchers.Main).launch {
                viewModel.loadEpisode(episodeId)


                viewModel.episode?.let { episode ->
                    binding.tvName.text = episode.name
                    binding.tvAirDate.text = episode.air_date
                    binding.tvEpisode.text = episode.episode
                }


                val characterImages = viewModel.characterImages
                if (characterImages.isNotEmpty()) {

                    val adapter = CharacterImageAdapter(characterImages) { characterDetails ->

                        openCharacterDetails(characterDetails)
                    }
                    binding.rvEpisodes.layoutManager =
                        GridLayoutManager(this@EpisodioDetalleActivity, 3)
                    binding.rvEpisodes.adapter = adapter

                }
            }
        }

    }

    fun openCharacterDetails(characterDetails: Personaje) {
        val intent = Intent(this, DetallesPersonajeActivity::class.java).apply {
            putExtra("CHARACTER_NAME", characterDetails.name)
            putExtra("CHARACTER_STATUS", characterDetails.status)
            putExtra("CHARACTER_GENDER", characterDetails.gender)
        }
        startActivity(intent)

    }
}
