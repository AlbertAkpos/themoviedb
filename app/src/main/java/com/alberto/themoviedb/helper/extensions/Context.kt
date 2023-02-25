package com.alberto.themoviedb.helper.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

internal fun Context.showDialog(
    title: String,
    message: String,
    negativeTitle: String = "",
    positiveTitle: String = "Retry",
    positiveCallback: () -> Unit
) {
    val builder = AlertDialog.Builder(this)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setNegativeButton(negativeTitle) { dialog, which ->
        dialog.dismiss()
    }
    builder.setPositiveButton(positiveTitle) { dialog, which ->
        dialog.dismiss()
        positiveCallback()
    }
    builder.show()
}