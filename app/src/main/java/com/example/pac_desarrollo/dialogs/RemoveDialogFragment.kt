package com.example.pac_desarrollo.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment


class RemoveDialogFragment(
    private val title: String,
    private val message: String,
    private val POSITIVE: String,
    private val NEGATIVE: String
) : DialogFragment() {

    internal lateinit var listener: RemoveDialogListener

    interface RemoveDialogListener {
        fun onRemoveDialogPositiveClick(dialog: DialogFragment)
        fun onRemoveDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setTitle(title).setMessage(message)
                .setPositiveButton(POSITIVE,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onRemoveDialogPositiveClick(this)
                    })
                .setNegativeButton(NEGATIVE,
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onRemoveDialogNegativeClick(this)
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = context as RemoveDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }
}