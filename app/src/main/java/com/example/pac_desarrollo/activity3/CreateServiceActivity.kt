package com.example.pac_desarrollo.activity3

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.example.pac_desarrollo.R

class CreateServiceActivity : AppCompatActivity() {
    var musicPlayerService: MusicPlayerService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_service)

        val intent = Intent(this, MusicPlayerService::class.java)
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE)

        val backBtn = this.findViewById<Button>(R.id.create_service_btnBack)
        backBtn.setOnClickListener { finish() }

        val playBtn = this.findViewById<Button>(R.id.create_service_btnPlay)
        playBtn.setOnClickListener { musicPlayerService?.play() }

        val pauseBtn = this.findViewById<Button>(R.id.create_service_btnPause)
        pauseBtn.setOnClickListener { musicPlayerService?.pause() }

        val sleepBtn = this.findViewById<Button>(R.id.create_service_btnSleep)
        sleepBtn.setOnClickListener { musicPlayerService?.sleep() }
    }

    private val myConnection = object: ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as MusicPlayerService.MyLocalBinder
            musicPlayerService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
        }
    }



}