package com.example.pac_desarrollo.activity2.crud

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.pac_desarrollo.R
import kotlinx.android.synthetic.main.activity_insert_item.*

class InsertItemActivity(): AbstractCRUDActionActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_item)
        tableName = intent.getStringExtra("tableName").toString()
        setBackButtonAction(this.findViewById(R.id.insert_item_btnBack))
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