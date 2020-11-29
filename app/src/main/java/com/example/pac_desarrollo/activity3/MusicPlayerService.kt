package com.example.pac_desarrollo.activity3

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MusicPlayerService(): Service() {
    private var musicPlayer: MusicPlayer = MusicPlayer(this)
    private val myBinder = MyLocalBinder(this)

    fun play() {
        musicPlayer.play()
    }

    fun pause() {
        musicPlayer.pause()
    }

    fun sleep() {
        musicPlayer.sleep()
    }

    override fun onCreate() {
        super.onCreate()
        musicPlayer.start()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return myBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.close()
    }
}

class MyLocalBinder(val context: MusicPlayerService) : Binder() {
    fun getService() : MusicPlayerService {
        return context
    }
}
