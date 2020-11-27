package com.example.pac_desarrollo.activity3

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.pac_desarrollo.R
class MusicPlayerService(): Service() {
    private lateinit var mediaPlayer: MediaPlayer
    private val myBinder = MyLocalBinder()


    fun play() {
        if ( mediaPlayer.isPlaying){
            mediaPlayer.seekTo(0)
        }
        mediaPlayer?.start()
        Log.d("fer", "play")
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun sleep(){
        Thread.sleep(5000)
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this , R.raw.decline)
        play()
        Log.d("fer", "service created")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("fer", "service starting")
        // If we get killed, after returning from here, restart
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    inner class MyLocalBinder : Binder() {
        fun getService() : MusicPlayerService {
            return this@MusicPlayerService
        }
    }




}


