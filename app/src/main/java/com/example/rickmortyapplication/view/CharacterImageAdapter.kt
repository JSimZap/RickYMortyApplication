package com.example.rickmortyapplication.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.rickmortyapplication.R
import com.squareup.picasso.Picasso

class CharacterImageAdapter(
    private val characterUrls: List<String>
) : RecyclerView.Adapter<CharacterImageAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character_image, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val characterUrl = characterUrls[position]
        Picasso.get().load(characterUrl).into(holder.characterImage)

    }

    override fun getItemCount(): Int = characterUrls.size

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val characterImage: ImageView = itemView.findViewById(R.id.ivCharacterImage)
    }
}