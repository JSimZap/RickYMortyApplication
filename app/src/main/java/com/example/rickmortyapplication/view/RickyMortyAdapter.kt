package com.example.rickmortyapplication.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Episodio

class RickyMortyAdapter(
    private var episodes: List<Episodio>,
    private val onItemClick: (Episodio) -> Unit
) : RecyclerView.Adapter<RickyMortyAdapter.EpisodeViewHolder>() {


    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val episodeTitle: TextView = itemView.findViewById(R.id.NombreEpisodio)
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


    fun updateEpisodes(newEpisodes: List<Episodio>) {
        episodes = newEpisodes
        notifyDataSetChanged()
    }
}

