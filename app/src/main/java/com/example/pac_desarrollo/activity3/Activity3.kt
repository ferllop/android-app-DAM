package com.example.pac_desarrollo.activity3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.pac_desarrollo.MainActivity
import com.example.pac_desarrollo.R

class Activity3 : AppCompatActivity() {
    public lateinit var thread:MyThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
    }

    fun goToActivity1(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun goToCreateService(view: View) {
        thread = MyThread()
        thread.start()
        startActivity(Intent(this, CreateServiceActivity::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this, MusicPlayerService::class.java))
    }

    inner class MyThread:Thread() {
        override fun run(){
            startService()
        }

        private fun startService() {
            startService(Intent(this@Activity3, MusicPlayerService::class.java))
        }

        private fun other() {
            //this@Activity3.bindService(
                //Intent(getApplicationContext(), MusicPlayerService::class.java),
                //serviceConnection,
                //Context.BIND_AUTO_CREATE
            //)
        }

    }

}