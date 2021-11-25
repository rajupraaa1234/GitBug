package com.example.gitbug.Dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import com.example.gitbug.CallBack.CommonDialogListner
import com.example.gitbug.Dialog.CustomDialog
import com.example.gitbug.R
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class CustomDialog(var context: Context) {
    var commonDialogListner: CommonDialogListner
    val dialog: Dialog?
        get() {
            if (Companion.dialog == null) {
                Companion.dialog = Dialog(context)
            }
            return Companion.dialog
        }

    fun setDailog() {
        Companion.dialog!!.setContentView(R.layout.common_dailog)
        Companion.dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        Companion.dialog!!.setCanceledOnTouchOutside(false)
        val ifyes = Companion.dialog!!.findViewById<View>(R.id.common_dialog_yes) as TextView
        val ifNo = Companion.dialog!!.findViewById<View>(R.id.common_dialog_no) as TextView
        ifyes.setOnClickListener { commonDialogListner.OnYesClickListner() }
        ifNo.setOnClickListener {
            commonDialogListner.OnNoClickListner()
            Companion.dialog!!.dismiss()
        }
        Companion.dialog!!.show()
    }

    companion object {
        private var dialog: Dialog? = null
        private lateinit var dialogFragment: DialogFragment
        private val instance: CustomDialog? = null
    }

    init {
        commonDialogListner = context as CommonDialogListner
        Companion.dialog = Dialog(context)
        dialogFragment = DialogFragment()
    }
}