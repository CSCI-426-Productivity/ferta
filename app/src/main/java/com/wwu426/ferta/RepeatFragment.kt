package com.wwu426.ferta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class RepeatFragment :  DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout: View = inflater.inflate(R.layout.fragment_repeat, container, false)

        layout.findViewById<Button>(R.id.repeat_dialog_cancel_button).setOnClickListener {
            dismiss()
        }
        layout.findViewById<Button>(R.id.repeat_dialog_confirm_button).setOnClickListener {
            //Toast.makeText(context, "TODO: use results", Toast.LENGTH_SHORT).show()
            dismiss()
        }

        return layout
    }
}