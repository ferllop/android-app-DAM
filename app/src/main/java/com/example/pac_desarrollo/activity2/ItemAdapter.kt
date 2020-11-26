package com.example.pac_desarrollo.activity2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pac_desarrollo.R

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