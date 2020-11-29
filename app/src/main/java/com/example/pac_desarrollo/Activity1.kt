package com.example.pac_desarrollo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.pac_desarrollo.activity2.Activity2
import com.example.pac_desarrollo.activity3.Activity3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToDatabaseActivity(view: View){
        startActivity(Intent(this, Activity2::class.java))
    }

    fun goToServiceActivity(view: View){
        startActivity(Intent(this, Activity3::class.java))
    }
}