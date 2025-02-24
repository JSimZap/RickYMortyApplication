package com.example.rickmortyapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rickmortyapplication.R

class DetallesPersonajeActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_personaje)

        val name = intent.getStringExtra("CHARACTER_NAME")
        val status = intent.getStringExtra("CHARACTER_STATUS")
        val gender = intent.getStringExtra("CHARACTER_GENDER")

        findViewById<TextView>(R.id.tvCharacterName).text = "Name: $name"
        findViewById<TextView>(R.id.tvCharacterStatus).text = "Status: $status"
        findViewById<TextView>(R.id.tvCharacterGender).text = "Gender: $gender"
    }
}
