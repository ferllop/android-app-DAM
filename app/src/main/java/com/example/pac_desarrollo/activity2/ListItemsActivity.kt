package com.example.pac_desarrollo.activity2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pac_desarrollo.R
import java.util.*

interface OnItemClickListener{
    fun onItemClicked(item: Item)
}

class ListItemsActivity : AppCompatActivity(), OnItemClickListener {
    private lateinit var db: AdminSQLiteOpenHelper
    private lateinit var tableName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        db = AdminSQLiteOpenHelper(this)
        tableName = intent.getStringExtra("tableName").toString()

        showAllData()

        val backBtn = this.findViewById<Button>(R.id.list_items_btnBack)
        backBtn.setOnClickListener { finish() }
    }

    override fun onRestart() {
        super.onRestart()
        showAllData()
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }

    fun showAllData(){
        var dataset: ArrayList<Item> = getAllData()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.adapter = ItemAdapter(dataset, this)
        recyclerView.setHasFixedSize(true)
    }

    fun getAllData(): ArrayList<Item> {
        var dbData = ArrayList<Item>()
        var rows = db.writableDatabase.rawQuery("SELECT rowid, * FROM $tableName", null)
        with(rows) {
            while(moveToNext()){
                val rowid: Int = getInt(getColumnIndex("rowid"))
                val description: String = getString(getColumnIndex("description"))
                val price: Float = getFloat(getColumnIndex("price"))
                dbData.add(Item(rowid, description, price))
            }
        }
        return dbData
    }

    override fun onItemClicked(item: Item) {
        startActivity(
            Intent(this, EditItemActivity::class.java).putExtra("idItem", item.rowid).putExtra(
                "tableName",
                tableName
            )
        )
    }

}