package com.example.rickmortyapplication.main

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.view.EpisodesActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnEpisodes: Button = findViewById(R.id.btnEpisodes)

        btnEpisodes.setOnClickListener {
            val intent = Intent(this, EpisodesActivity::class.java)
            startActivity(intent)
        }
    }
}
