package com.example.pac_desarrollo.activity2.crud

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pac_desarrollo.R
import com.example.pac_desarrollo.activity2.Item
import java.util.*

interface OnItemClickListener{
    fun onItemClicked(item: Item)
}

class ListItemsActivity : AbstractCRUDActionActivity(), OnItemClickListener {
    class ItemAdapter(
        private val dataset: List<Item>,
        private val clickListener: OnItemClickListener
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.lbl_item_description)

            fun bind(item: Item, clickListener: OnItemClickListener){
                textView.text =  item.toString()
                textView.setOnClickListener {
                    clickListener.onItemClicked(item)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val adapterLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item, parent, false)
            return ItemViewHolder(adapterLayout)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = dataset[position]
            holder.bind(item, clickListener)
        }

        override fun getItemCount() = dataset.size
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_items)

        db = AdminSQLiteOpenHelper(this)
        tableName = intent.getStringExtra("tableName").toString()

        showAllData()

        setBackButtonAction(this.findViewById(R.id.list_items_btnBack))
    }

    override fun onRestart() {
        super.onRestart()
        showAllData()
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