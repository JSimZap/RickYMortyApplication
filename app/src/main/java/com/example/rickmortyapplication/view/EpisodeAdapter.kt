package com.example.rickmortyapplication.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Episodio

class EpisodesAdapter(private var episodes: List<Episodio>) : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val episode = episodes[position]
        holder.bind(episode)
        holder.itemView.setOnClickListener {
            val intent = Intent(context, EpisodioDetalleActivity::class.java)
            intent.putExtra("episode", episode)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = episodes.size


    fun updateEpisodes(newEpisodes: List<Episodio>) {
        episodes = newEpisodes
        notifyDataSetChanged()
    }

    inner class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val episodeName: TextView = itemView.findViewById(R.id.episodeName)

        fun bind(episode: Episodio) {
            episodeName.text = episode.name
        }
    }
}
