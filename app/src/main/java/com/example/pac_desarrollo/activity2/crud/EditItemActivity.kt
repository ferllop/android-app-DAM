package com.example.pac_desarrollo.activity2.crud

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.pac_desarrollo.R
import com.example.pac_desarrollo.activity2.Item
import kotlinx.android.synthetic.main.activity_insert_item.*
import kotlin.properties.Delegates

class EditItemActivity : AbstractCRUDActionActivity() {

    private var idItem by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_item)

        tableName = intent.getStringExtra("tableName").toString()
        idItem = intent.getIntExtra("idItem", -1)

        val item: Item = db.getItem(tableName, idItem)

        val txtDescription = findViewById<TextView>(R.id.txtDescription)
        txtDescription.text =item.description
        val txtPrice = findViewById<TextView>(R.id.txtPrice)
        txtPrice.text =item.price.toString()

        val insertBtn = this.findViewById<Button>(R.id.btnInsertDataAct_InsertData)
        insertBtn.text = getString(R.string.editData)

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