package com.wwu426.ferta

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
        var layout = inflater.inflate(R.layout.fragment_settings, container, false)

        val unavailabilityButton: Button = layout.findViewById(R.id.button_unavailability_change)
        unavailabilityButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.settings_view, UnavailableFragment(), "tag").commit()
        }


        return layout
    }
}