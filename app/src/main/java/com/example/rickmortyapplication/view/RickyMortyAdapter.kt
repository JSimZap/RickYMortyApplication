package com.example.rickmortyapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Episodio

/**
 * Adaptador para la lista de episodios en un RecyclerView
 * Permite mostrar los episodios y manejar eventos de click en cada uno
 *
 * @property episodes Lista de episodios a mostrar
 * @property onItemClick Funcion de callback que se ejecuta cuando se hace click en un episodio
 */
class RickyMortyAdapter(
    private var episodes: List<Episodio>,
    private val onItemClick: (Episodio) -> Unit
) : RecyclerView.Adapter<RickyMortyAdapter.EpisodeViewHolder>() {


    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeTitle: TextView = itemView.findViewById(R.id.episodeName)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.episode_item, parent, false)
        return EpisodeViewHolder(view)
    }


    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.episodeTitle.text = episode.name
        holder.itemView.setOnClickListener {
            onItemClick(episode)
        }
    }


    override fun getItemCount(): Int = episodes.size


    /**
     * Actualiza la lista de episodios y notifica al adaptador para refrescar la vista
     *
     * @param newEpisodes Nueva lista de episodios
     */
    fun updateEpisodes(newEpisodes: List<Episodio>) {
        episodes = newEpisodes
        notifyDataSetChanged()
    }
}

