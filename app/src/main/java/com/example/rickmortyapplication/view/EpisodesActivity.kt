package com.example.rickmortyapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Episodio
import com.example.rickmortyapplication.model.RetrofitInstance
import kotlinx.coroutines.launch


/**
 * Activity que muestra la lista de episodios de la serie Rick and Morty
 * Permite filtrar los episodios por temporada
 */
class EpisodesActivity : AppCompatActivity() {
    private lateinit var temporadaAdapter: ArrayAdapter<String>
    private val listaEpisodios = mutableListOf<Episodio>()
    private lateinit var episodioAdapter: EpisodesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episodes)


        val spinnerSeasons: Spinner = findViewById(R.id.spinnerSeasons)
        val episodesRecyclerView: RecyclerView = findViewById(R.id.episodesList)


        episodioAdapter = EpisodesAdapter(listaEpisodios)
        episodesRecyclerView.layoutManager = LinearLayoutManager(this)
        episodesRecyclerView.adapter = episodioAdapter


        obtenerEpisodios(1)


        spinnerSeasons.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedSeason = temporadaAdapter.getItem(position).toString()
                mostrarEpisodios(selectedSeason)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        findViewById<Button>(R.id.btnVolver).setOnClickListener {
            finish()
        }
    }

    /**
     * Obtiene los episodios de la API de manera recursiva si hay múltiples paginas
     * @param page Número de la pagina actual que se está consultando
     */
    @SuppressLint("NotifyDataSetChanged")
    private fun obtenerEpisodios(page: Int) {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getEpisodes(page)
                listaEpisodios.addAll(response.resultado)
                episodioAdapter.notifyDataSetChanged()

                // Caragamos por si hay mas paginas
                response.info.next?.let {
                    val nextPage = page + 1
                    obtenerEpisodios(nextPage)
                } ?: run {

                    //Filtramos las temporadas
                    val seasons = listaEpisodios.map { it.episode.substring(0, 4) }.distinct()
                    temporadaAdapter = ArrayAdapter(
                        this@EpisodesActivity,
                        android.R.layout.simple_spinner_item,
                        seasons
                    )
                    temporadaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    findViewById<Spinner>(R.id.spinnerSeasons).adapter = temporadaAdapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Filtra los episodios según la temporada seleccionada en el Spinner y actualiza el RecyclerView
     * @param season Temporada seleccionada en el Spinner.
     */
    private fun mostrarEpisodios(season: String) {
        val filteredEpisodes = listaEpisodios.filter { it.episode.startsWith(season) }
        episodioAdapter.updateEpisodes(filteredEpisodes)
    }
}


