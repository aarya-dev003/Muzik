package com.example.muzik.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.muzik.databinding.ActivityPlaylistBinding

class PlaylistActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityPlaylistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}