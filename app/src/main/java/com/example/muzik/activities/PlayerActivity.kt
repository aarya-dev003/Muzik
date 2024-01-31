package com.example.muzik.activities

import android.os.Bundle
import android.view.View
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.bumptech.glide.Glide
import com.example.muzik.MyExoplayer
import com.example.muzik.R
import com.example.muzik.databinding.ActivityPlayerBinding


class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    lateinit var exoPlayer: ExoPlayer
    var playerListner = object: Player.Listener{
        override fun onIsPlayingChanged(isPlaying: Boolean) {
            super.onIsPlayingChanged(isPlaying)
            showGif(isPlaying)
        }
    }
    @OptIn(UnstableApi::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MyExoplayer.getCurrentSong()?.apply {
            binding.songTitleTextView.text = title
            binding.songSubtitleTextView.text = subtitle
            Glide.with(binding.songImage).load(coverUrl)
                .circleCrop()
                .into(binding.songImage)
            Glide.with(binding.songGifImageView).load(R.drawable.media_playing)
                .circleCrop()
                .into(binding.songGifImageView)
            exoPlayer = MyExoplayer.getInstance()!!
            binding.playerControl.player = exoPlayer
            binding.playerControl.showController()
            exoPlayer.addListener(playerListner)
//            val shutterView = binding.playerControl.findViewById<View>(androidx.media3.ui.R.id.exo_shutter)
//            shutterView.setBackgroundColor(Color.parseColor("#19165b"))
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.removeListener(playerListner)
    }
    fun showGif(show : Boolean){
        if(show)
            binding.songGifImageView.visibility = View.VISIBLE
        else
            binding.songGifImageView.visibility = View.INVISIBLE
    }
}