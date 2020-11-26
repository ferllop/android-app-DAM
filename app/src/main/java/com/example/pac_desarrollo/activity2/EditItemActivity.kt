package com.example.pac_desarrollo.activity2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pac_desarrollo.R
import kotlinx.android.synthetic.main.activity_insert_item.*
import kotlin.properties.Delegates

class EditItemActivity : AppCompatActivity() {
    private lateinit var db: AdminSQLiteOpenHelper
    private lateinit var tableName: String
    private var idItem by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_item)

        tableName = intent.getStringExtra("tableName").toString()
        idItem = intent.getIntExtra("idItem", -1)
        db = AdminSQLiteOpenHelper(this)
        val item: Item = db.getItem(tableName, idItem)

        val txtDescription = findViewById<TextView>(R.id.txtDescription)
        txtDescription.text =item.description
        val txtPrice = findViewById<TextView>(R.id.txtPrice)
        txtPrice.text =item.price.toString()

        val backBtn = this.findViewById<Button>(R.id.insert_item_btnBack)
        backBtn.setOnClickListener { finish() }
        val insertBtn = this.findViewById<Button>(R.id.btnInsertDataAct_InsertData)
        insertBtn.text = getString(R.string.editData)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
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

        val item = Item(idItem, description, price.toFloat())

        db.updateItem(tableName, item)
        Toast.makeText(applicationContext, getString(R.string.editSuccesfull), Toast.LENGTH_SHORT).show()


    }

    private fun isDataValid(): Boolean{
        val description = txtDescription.text.toString()
        val price = txtPrice.text.toString()
        return !description.isEmpty() && !price.isEmpty() && price.toFloat() > 0
    }
}