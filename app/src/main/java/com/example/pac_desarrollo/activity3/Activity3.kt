package com.example.pac_desarrollo.activity3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pac_desarrollo.MainActivity
import com.example.pac_desarrollo.R

class Activity3 : AppCompatActivity() {
    private lateinit var player: MusicPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
    }

    fun goToActivity1(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun createService(view: View) {
        createService()
    }

    private fun createService() {
        if (player == null) {
            player = MusicPlayer(this, R.raw.decline)
            player.play()
        }
    }

    fun stopService(view: View) {
        stopService()
    }

    private fun stopService() {
        player.stop()
        player.onDestroy()
    }
}