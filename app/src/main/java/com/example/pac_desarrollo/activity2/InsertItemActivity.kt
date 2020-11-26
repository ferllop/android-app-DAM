package com.example.pac_desarrollo.activity2

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.pac_desarrollo.R
import kotlinx.android.synthetic.main.activity_insert_item.*

class InsertItemActivity() : AppCompatActivity(){
    private lateinit var db: AdminSQLiteOpenHelper
    private lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_item)

        db = AdminSQLiteOpenHelper(this)
        tableName = intent.getStringExtra("tableName").toString()

        val backBtn = this.findViewById<Button>(R.id.insert_item_btnBack)
        backBtn.setOnClickListener { finish() }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }

    fun insertData(view: View){
        val description = txtDescription.text.toString()
        val price = txtPrice.text.toString()
        if(!isDataValid()) {
            var message: String = ""
            if (description.isEmpty()) {
                message += getString(R.string.descriptionNotValid) + "\n"
            }
            if (price.isEmpty() || price.toFloat() < 0) {
                message += getString(R.string.priceNotValid)
            }
            Toast.makeText(applicationContext, message.trim(), Toast.LENGTH_SHORT).show()
            return
        }

        val data = ContentValues()
        data.put("description", description)
        data.put("price", price)
        db.writableDatabase.insert(tableName, null, data)
        txtDescription.text.clear()
        txtPrice.text.clear()
    }

    private fun isDataValid(): Boolean{
        val description = txtDescription.text.toString()
        val price = txtPrice.text.toString()
        return !description.isEmpty() && !price.isEmpty() && price.toFloat() > 0
    }

}