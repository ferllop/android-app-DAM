package com.example.pac_desarrollo.activity3

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.example.pac_desarrollo.R

class MusicPlayer(val context: Context) : Thread() {

    enum class PlayerAction {
        NULL,
        PLAY,
        PAUSE,
        SLEEP,
        DESTROY
    }

    private val TAG = "ilerna: THREAD"
    private var action = PlayerAction.NULL
    private lateinit var mediaPlayer: MediaPlayer

    override fun run() {
        mediaPlayer = MediaPlayer.create(context, R.raw.decline)
        while (action != PlayerAction.DESTROY) {
            when (action) {
                PlayerAction.SLEEP -> mediaSleep()
                PlayerAction.PLAY -> mediaPlay()
                PlayerAction.PAUSE -> mediaPause()
                else -> {}
            }
        }
        close()
    }

    private fun finish() {
        mediaPlayer.release()
        currentThread().interrupt()
    }

    fun close() {
        action = PlayerAction.DESTROY
        finish()
    }

    fun play() {
        action = PlayerAction.PLAY
    }

    fun pause() {
        action = PlayerAction.PAUSE
    }

    fun sleep() {
        action = PlayerAction.SLEEP
    }

    fun mediaPlay() {
        action = PlayerAction.NULL
        if (mediaPlayer.isPlaying) {
            mediaPlayer.seekTo(0)
        }
        mediaPlayer.start()
    }

    fun mediaPause() {
        action = PlayerAction.NULL
        if(mediaPlayer.isPlaying){
            mediaPlayer.pause()
        }
    }

    fun mediaSleep(){
        action = PlayerAction.NULL
        val wasPlayingBeforeSleep = mediaPlayer.isPlaying
        if (mediaPlayer.isPlaying) {
            mediaPause()
        }
        Thread.sleep(5000)
        if(action != PlayerAction.DESTROY && wasPlayingBeforeSleep){
            mediaPlay()
        }
    }
}