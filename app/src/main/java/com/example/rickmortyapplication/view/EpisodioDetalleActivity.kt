package com.example.rickmortyapplication.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Episodio
import com.example.rickmortyapplication.model.Personaje
import com.example.rickmortyapplication.model.RetrofitInstance
import kotlinx.coroutines.launch

class EpisodioDetalleActivity : AppCompatActivity() {
    private lateinit var episode: Episodio
    private val charactersList: MutableList<Personaje> = mutableListOf<Personaje>()
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodio_detalle)


        episode = intent.getSerializableExtra("episode") as Episodio


        val charactersRecyclerView: RecyclerView = findViewById(R.id.charactersRecyclerView)
        charactersAdapter = CharactersAdapter(charactersList)
        charactersRecyclerView.layoutManager = LinearLayoutManager(this)
        charactersRecyclerView.adapter = charactersAdapter


        findViewById<TextView>(R.id.episodeName).text = episode.name
        findViewById<TextView>(R.id.episodeAirDate).text = episode.airDate


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
                        image = response.image
                    )
                }

                charactersList.clear()
                charactersList.addAll(characterDetails)
                charactersAdapter.updateCharacters(characterDetails)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}
