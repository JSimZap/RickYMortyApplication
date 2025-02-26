package com.example.rickmortyapplication.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.ActivityPersonajeDetalleBinding
import com.example.rickmortyapplication.model.Personaje
import com.example.rickmortyapplication.model.RetrofitInstance
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonajeDetalleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonajeDetalleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPersonajeDetalleBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val personaje = intent.getSerializableExtra("personaje") as? Personaje

        if (personaje != null) {
            binding.txtNombre.text = personaje.name
            binding.txtEstado.text = "Estado: ${personaje.status}"
            binding.txtEspecie.text = "Especie: ${personaje.species}"
            binding.txtGenero.text = "Género: ${personaje.gender}"
            Picasso.get().load(personaje.image).into(binding.imgPersonaje)

        }

    }
}
