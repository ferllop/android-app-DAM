package com.example.pac_desarrollo.activity2.crud

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

abstract class AbstractCRUDActionActivity() : AppCompatActivity() {
    protected lateinit var db: AdminSQLiteOpenHelper
    protected lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = AdminSQLiteOpenHelper(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }

    protected fun setBackButtonAction(backButton: Button){
        backButton.setOnClickListener { finish() }
    }

}