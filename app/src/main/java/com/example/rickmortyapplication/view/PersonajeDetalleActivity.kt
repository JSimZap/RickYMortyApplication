package com.example.rickmortyapplication.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rickmortyapplication.databinding.ActivityPersonajeDetalleBinding
import com.example.rickmortyapplication.model.Personaje
import com.squareup.picasso.Picasso

class PersonajeDetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonajeDetalleBinding


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonajeDetalleBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val personaje = intent.getSerializableExtra("personaje") as Personaje


            binding.txtNombre.text = personaje.name
            binding.txtEstado.text = "Estado: ${personaje.status}"
            binding.txtEspecie.text = "Especie: ${personaje.species}"
            binding.txtGenero.text = "Género: ${personaje.gender}"
            Picasso.get().load(personaje.image).into(binding.imgPersonaje)

            binding.btnAtras.setOnClickListener(){
                finish()
            }

    }
}
