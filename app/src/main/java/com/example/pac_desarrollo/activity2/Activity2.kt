package com.example.pac_desarrollo.activity2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.pac_desarrollo.Activity1
import com.example.pac_desarrollo.R
import com.example.pac_desarrollo.activity2.crud.CreateTableActivity
import com.example.pac_desarrollo.activity2.crud.InsertItemActivity
import com.example.pac_desarrollo.activity2.crud.ListItemsActivity
import com.example.pac_desarrollo.dialogs.RemoveDialogFragment
import com.example.pac_desarrollo.dialogs.TwoOptionsDialogFragment

class Activity2 : AppCompatActivity(), TwoOptionsDialogFragment.NoticeDialogListener,
    RemoveDialogFragment.RemoveDialogListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        Toast.makeText(applicationContext, R.string.youAreInActivity2, Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(applicationContext, R.string.youAreInActivity2, Toast.LENGTH_LONG).show()
    }

    fun goToActivity1(view: View){
        startActivity(Intent(this, Activity1::class.java))
    }

    fun goToCreateTableActivity(view: View) {
        val tableInUse = getSharedPreferences("app", Context.MODE_PRIVATE).contains("table_name")
        if (tableInUse) {
            val tableInUse = getSharedPreferences("app", Context.MODE_PRIVATE).getString("table_name", "").toString()
            RemoveDialogFragment(
                getString(R.string.tableIsInUseTitle),
                getString(R.string.tableIsInUseMessage, tableInUse),
                getString(R.string.deleteAndCreate),
                getString(R.string.stayAsIs)
            ).show(supportFragmentManager, "tableInUse")
        } else {
            startActivity(Intent(this, CreateTableActivity::class.java))
        }
    }

    fun goToShowDataActivity(view: View) {
        val hasTable = getSharedPreferences("app", Context.MODE_PRIVATE).contains("table_name")
        if (hasTable) {
            val tableName = getSharedPreferences("app", Context.MODE_PRIVATE).getString("table_name", "").toString()
            startActivity(Intent(this, ListItemsActivity::class.java).putExtra("tableName", tableName))
        } else {
            TwoOptionsDialogFragment(
                getString(R.string.firstCreateTableTitle),
                getString(R.string.firstCreateTableMessage),
                getString(R.string.yes),
                getString(R.string.no)
            ).show(supportFragmentManager, "createTable")
        }
    }


    fun goToInsertDataActivity(view: View) {
        val hasTable = getSharedPreferences("app", Context.MODE_PRIVATE).contains("table_name")
        if (hasTable) {
            val tableName = getSharedPreferences("app", Context.MODE_PRIVATE).getString("table_name", "").toString()
            startActivity(Intent(this, InsertItemActivity::class.java).putExtra("tableName", tableName))
        } else {
            TwoOptionsDialogFragment(
                getString(R.string.firstCreateTableTitle),
                getString(R.string.firstCreateTableMessage),
                getString(R.string.yes),
                getString(R.string.no)
            ).show(supportFragmentManager, "createTable")
        }

    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        startActivity(Intent(this, CreateTableActivity::class.java))
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        // Do nothing
    }

    override fun onRemoveDialogPositiveClick(dialog: DialogFragment) {
        val tableInUse = getSharedPreferences("app", Context.MODE_PRIVATE).getString("table_name", "").toString()
        startActivity(Intent(this, CreateTableActivity::class.java).putExtra("removeTableInUse", true))
    }

    override fun onRemoveDialogNegativeClick(dialog: DialogFragment) {
        // Do nothing
    }
}