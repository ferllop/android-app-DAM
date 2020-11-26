package com.example.pac_desarrollo.activity3

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.view.View

class MusicPlayer: Service {
    private lateinit var mediaPlayer: MediaPlayer

    constructor(context: Context, fileName:Int){
        super.onCreate()
        mediaPlayer = MediaPlayer.create(context, fileName)
    }


    fun play() {
        mediaPlayer?.start() // no need to call prepare(); create() does that for you
    }

    fun stop() {
        if (mediaPlayer?.isPlaying) {
            mediaPlayer?.stop()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
}