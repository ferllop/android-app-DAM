package com.example.pac_desarrollo.activity2

class Item(public val rowid: Int, public val description: String, public val price: Float) {

    override fun toString(): String {
        return "${rowid.toString()} - $description - ${price.toString()}"
    }

}