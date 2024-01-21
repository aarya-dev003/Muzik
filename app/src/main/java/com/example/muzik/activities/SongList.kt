package com.example.muzik.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.muzik.adapters.SongListAdapter
import com.example.muzik.databinding.ActivitySongListBinding
import com.example.muzik.model.CategoryModel

class SongList : AppCompatActivity() {
    companion object{
        lateinit var category: CategoryModel
    }
    lateinit var songListAdapter: SongListAdapter
    lateinit var binding: ActivitySongListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameTextView.text = category.name
        Glide.with(binding.coverImageView).load(category.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(binding.coverImageView)
        setupSongsListRecyclerView()
    }
    fun setupSongsListRecyclerView(){
        songListAdapter = SongListAdapter(category.songs)
        binding.songsListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter = songListAdapter
    }
}