package com.wwu426.ferta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class NotificationFragment :  DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout: View = inflater.inflate(R.layout.fragment_notification, container, false)

        layout.findViewById<Button>(R.id.notification_dialog_cancel_button).setOnClickListener {
            dismiss()
        }
        layout.findViewById<Button>(R.id.notification_dialog_confirm_button).setOnClickListener {
            //Toast.makeText(context, "TODO: use results", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        layout.findViewById<Button>(R.id.notification_dialog_add_button).setOnClickListener {

            layout.findViewById<LinearLayout>(R.id.notifications_ll).addView( EditNotificationLayout(requireContext()) )

            if(layout.findViewById<LinearLayout>(R.id.notifications_ll).childCount >= 5) {
                layout.findViewById<Button>(R.id.notification_dialog_add_button).visibility = View.GONE
            }
            else {
                layout.findViewById<Button>(R.id.notification_dialog_add_button).visibility = View.VISIBLE
            }
        }

        return layout
    }
}