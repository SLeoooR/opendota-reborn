package com.scottandmarc.opendotareborn.toolbox.helpers

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.scottandmarc.opendotareborn.R

class DialogHelper {
    companion object {
        fun createLoadingDialog(
            context: Context,
            inflater: LayoutInflater,
        ): AlertDialog {
            val view: View = inflater.inflate(R.layout.loading_dialog, null, false)

            return MaterialAlertDialogBuilder(context).setView(view)
                .setCancelable(false)
                .create()
        }
        fun createRetryDialog(
            context: Context,
            title: String,
            message: String,
            buttonText: String,
            buttonOnClick: DialogInterface.OnClickListener?
        ): AlertDialog {
            return MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(buttonText, buttonOnClick)
                .create()
        }
    }
}