package com.example.pac_desarrollo.activity3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.pac_desarrollo.MainActivity
import com.example.pac_desarrollo.R
import kotlin.concurrent.thread

class Activity3 : AppCompatActivity() {

    private val TAG = "ilerna: Activity3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)
    }

    fun goToActivity1(view: View) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun goToCreateService(view: View) {
        Log.d(TAG, android.os.Process.getThreadPriority(android.os.Process.myTid()).toString());
        startService(Intent(this, MusicPlayerService::class.java))
        startActivity(Intent(this, CreateServiceActivity::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this, MusicPlayerService::class.java))
    }



}

