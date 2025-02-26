package com.example.rickmortyapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.ActivityEpisodioDetalleBinding
import com.example.rickmortyapplication.model.Episodio
import com.example.rickmortyapplication.model.Personaje
import com.example.rickmortyapplication.model.RetrofitInstance
import kotlinx.coroutines.launch

class EpisodioDetalleActivity : AppCompatActivity() {
    private lateinit var episode: Episodio
    private val charactersList: MutableList<Personaje> = mutableListOf<Personaje>()
    private lateinit var personajeAdapter: PersonajeAdapter
    private lateinit var binding: ActivityEpisodioDetalleBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodioDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)


        episode = intent.getSerializableExtra("episode") as Episodio


        binding.episodeName.text = episode.name
        binding.episodeAirDate.text = episode.airDate
        binding.temporadaEpisodio.text = episode.episode

        val charactersRecyclerView: RecyclerView = findViewById(R.id.charactersRecyclerView)
        personajeAdapter = PersonajeAdapter(charactersList) { personaje ->
            val intent = Intent(this, PersonajeDetalleActivity::class.java)
            intent.putExtra("personaje", personaje)
            startActivity(intent)
        }
        charactersRecyclerView.layoutManager = LinearLayoutManager(this)
        charactersRecyclerView.adapter = personajeAdapter



        obetenrPersonajes()

        findViewById<Button>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }


    private fun obetenrPersonajes() {
        lifecycleScope.launch {
            try {
                val characterDetails = episode.characters.map { characterUrl ->
                    val characterId = characterUrl.substringAfterLast("/").toInt()

                    // Hacer la solicitud para obtener los detalles del personaje
                    val response = RetrofitInstance.api.getCharacterDetails(characterId)

                    Personaje(
                        id = response.id,
                        name = response.name,
                        image = response.image,
                        status = response.status,
                        species = response.species,
                        gender = response.gender
                    )
                }

                charactersList.clear()
                charactersList.addAll(characterDetails)
                personajeAdapter.updateCharacters(characterDetails)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
