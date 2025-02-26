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
 * Adaptador para la lista de personajes
 * @param personajes Lista inicial de personajes
 * @param onItemClick Acción a ejecutar cuando se hace clic en un personaje
 */

class PersonajeAdapter(
    private var personajes: List<Personaje>,
    private val onItemClick: (Personaje) -> Unit
) : RecyclerView.Adapter<PersonajeAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = personajes[position]
        holder.nombrePersonaje.text = character.name
        Picasso.get().load(character.image).into(holder.imagenPersonaje)
        holder.itemView.setOnClickListener {
            onItemClick(character)
        }
    }

    override fun getItemCount(): Int = personajes.size


    /**
     * Actualiza la lista de personajes en el adaptador
     * @param nuevoPersonaje Nueva lista de personajes
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateCharacters(nuevoPersonaje: List<Personaje>) {
        personajes = nuevoPersonaje
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenPersonaje: ImageView = itemView.findViewById(R.id.characterImage)
        val nombrePersonaje: TextView = itemView.findViewById(R.id.characterName)

        fun bind(character: Personaje) {
            nombrePersonaje.text = character.name

            Picasso.get().load(character.image).into(imagenPersonaje)

        }
    }
}
