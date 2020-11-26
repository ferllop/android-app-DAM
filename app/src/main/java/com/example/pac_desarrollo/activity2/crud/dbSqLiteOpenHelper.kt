package com.example.pac_desarrollo.activity2.crud

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import com.example.pac_desarrollo.activity2.Item

class AdminSQLiteOpenHelper(private val context: Context)
    : SQLiteOpenHelper(context, "mydb", null, 3) {
    private lateinit var tableName: String

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table IF EXISTS $tableName")
        onCreate(db)
    }

    fun hasTable(tableName: String): Boolean {
        val result = writableDatabase.rawQuery("select tbl_name from sqlite_master WHERE tbl_name='$tableName'", null)
        return result.count > 0
    }

    fun createTable(tableName: String){
        this.tableName = tableName
        writableDatabase.execSQL("CREATE TABLE ${tableName}(description TEXT, price REAL)")
    }

    fun recreateTable(tableName: String){
        this.removeTable(tableName)
        this.createTable(tableName)
    }

    fun removeTable(tableName: String) {
        writableDatabase.execSQL("drop table IF EXISTS $tableName")
    }

    fun getItem(tableName: String, id: Int): Item {
        val row = writableDatabase.rawQuery("SELECT rowid, * FROM ${tableName} WHERE rowid = $id", null)
        row.moveToNext()
        val rowid = row.getInt(row.getColumnIndex("rowid"))
        val description = row.getString(row.getColumnIndex("description"))
        val price = row.getFloat(row.getColumnIndex("price"))
        return Item(rowid, description, price)
    }

    fun updateItem(tableName: String, item: Item) {
        val query = "UPDATE $tableName SET description='${item.description}', price=${item.price} WHERE rowid=${item.rowid}"
        writableDatabase.execSQL(query)
    }
}

