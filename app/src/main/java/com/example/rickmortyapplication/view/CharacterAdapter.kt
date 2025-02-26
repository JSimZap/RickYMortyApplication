package com.example.rickmortyapplication.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.model.Personaje
import com.squareup.picasso.Picasso

/**
 * Adaptador para la lista de personajes en un RecyclerView.
 * @param characters Lista inicial de personajes.
 * @param onItemClick Acción a ejecutar cuando se hace clic en un personaje.
 */

class CharactersAdapter(
    private var characters: List<Personaje>,
    private val onItemClick: (Personaje) -> Unit
) : RecyclerView.Adapter<CharactersAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.characterName.text = character.name
        Picasso.get().load(character.image).into(holder.characterImage)
        holder.itemView.setOnClickListener {
            onItemClick(character)
        }
    }

    override fun getItemCount(): Int = characters.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateCharacters(newCharacters: List<Personaje>) {
        characters = newCharacters
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: ImageView = itemView.findViewById(R.id.characterImage)
        val characterName: TextView = itemView.findViewById(R.id.characterName)

        fun bind(character: Personaje) {
            characterName.text = character.name

            Picasso.get().load(character.image).into(characterImage)

        }
    }
}
