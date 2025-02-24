package com.example.rickmortyapplication.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.databinding.ActivityMainBinding
import com.example.rickmortyapplication.model.Episodio
import com.example.rickmortyapplication.view.EpisodioDetalleActivity
import com.example.rickmortyapplication.view.RickyMortyAdapter
import com.example.rickmortyapplication.viewModel.EpisodiosViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: EpisodiosViewModel by viewModels()
    private lateinit var adapter: RickyMortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = RickyMortyAdapter(viewModel.getEpisodes()) { episode ->
            openEpisodeDetails(episode)
        }
        binding.rvEpisodes.layoutManager = LinearLayoutManager(this)
        binding.rvEpisodes.adapter = adapter


        lifecycleScope.launch {
            viewModel.loadFirstPage()
            adapter.updateEpisodes(viewModel.getEpisodes())
        }


        binding.rvEpisodes.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount

                // Cargar más episodios si el usuario llega al final de la lista
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    lifecycleScope.launch {
                        viewModel.loadNextPage()
                        adapter.updateEpisodes(viewModel.getEpisodes())
                    }
                }
            }
        })
    }

    private fun openEpisodeDetails(episode: Episodio) {
        val intent = Intent(this, EpisodioDetalleActivity::class.java).apply {
            putExtra("EPISODE_ID", episode.id)
        }
        startActivity(intent)
    }
}