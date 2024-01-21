package com.example.muzik

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.muzik.model.SongModel

object MyExoplayer {
    private var exoplayer: ExoPlayer? = null
    private var currentSong: SongModel? = null

    fun getCurrentSong() : SongModel? {
        return currentSong
    }

    fun getInstance(): ExoPlayer? {
        return exoplayer
    }

    fun startPlaying(context: Context, song: SongModel) {
        if (exoplayer == null) {
            exoplayer = ExoPlayer.Builder(context).build()
        }

        if(currentSong!=song){ // only new song will play if clicked
            currentSong = song

            currentSong?.url?.apply {
                val mediaItem = MediaItem.fromUri(this)
                exoplayer?.setMediaItem(mediaItem)
                exoplayer?.prepare()
                exoplayer?.play()

            }
        }
    }

    fun releasePlayer() {
        exoplayer?.release()
        exoplayer = null
    }
}
