package com.wwu426.ferta

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_settings, container, false)
        val buttonLogOut = layout.findViewById<Button>(R.id.button_logout)
        val unavailabilityButton: Button = layout.findViewById(R.id.button_unavailability_change)

        unavailabilityButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.settings_view, UnavailableFragment(), "tag").commit()
        }
        buttonLogOut.setOnClickListener {
            startActivity(Intent(context, AuthActivity::class.java))
        }

        return layout
    }
}