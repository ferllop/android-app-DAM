package com.example.pac_desarrollo.activity3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.pac_desarrollo.Activity1
import com.example.pac_desarrollo.R

class Activity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        Toast.makeText(this, R.string.youAreInActivity3, Toast.LENGTH_LONG).show()
    }

    fun goToActivity1(view: View) {
        startActivity(Intent(this, Activity1::class.java))
    }

    fun goToCreateService(view: View) {
        startService(Intent(this, MusicPlayerService::class.java))
        startActivity(Intent(this, CreateServiceActivity::class.java))
    }

    fun stopService(view: View) {
        stopService(Intent(this, MusicPlayerService::class.java))
    }
}

