package com.example.pac_desarrollo.activity2.crud

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pac_desarrollo.R
import com.example.pac_desarrollo.dialogs.TwoOptionsDialogFragment
import kotlinx.android.synthetic.main.activity_create_table.*

class CreateTableActivity : AbstractCRUDActionActivity(),
    TwoOptionsDialogFragment.NoticeDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_create_table)

        if (intent.getBooleanExtra("removeTableInUse", false)) {
            val tableInUse = getSharedPreferences("app", Context.MODE_PRIVATE).getString("table_name", "").toString()
            deleteTable(tableInUse)
        }

        setBackButtonAction(this.findViewById<Button>(R.id.create_table_btnBack))
    }

    fun createTable(view: View){
        tableName = txtTableName.text.toString()

        if (tableName.isEmpty()) {
            return
        }

        if (db.hasTable(tableName)) {
            showTableExistDialog(tableName)
        } else {
            db.createTable(tableName)
            val prefs = getSharedPreferences("app", Context.MODE_PRIVATE)
            prefs.edit().putString("table_name", tableName).apply()
            Toast.makeText(this, getString(R.string.tableWasCreated, tableName), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun deleteTable(view: View){
        deleteTable(txtTableName.text.toString())
    }

    fun deleteTable(tableName: String){
        if (tableName.isEmpty()) {
            return
        }

        if (db.hasTable(tableName)) {
            db.removeTable(tableName)
            Toast.makeText(this, getString(R.string.tableDeleted, tableName), Toast.LENGTH_SHORT).show()
            val prefs = getSharedPreferences("app", Context.MODE_PRIVATE)
            prefs.edit().remove("table_name").apply()
        } else {
            Toast.makeText(this, getString(R.string.tableNotExist, tableName), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showTableExistDialog(tableName: String){
        TwoOptionsDialogFragment(
            getString(R.string.tableExistTitle, tableName),
            getString(R.string.tableExistMessage),
            getString(R.string.yes),
            getString(R.string.no)
        ).show(supportFragmentManager, "createTable")
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        val prefs = getSharedPreferences("app", Context.MODE_PRIVATE)
        if (tableName != null) {
            prefs.edit().putString("table_name", tableName).apply()
        }

        db.recreateTable(tableName)
        Toast.makeText(this, getString(R.string.tableRecreatedDeleted, tableName), Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // Do nothing
    }
}