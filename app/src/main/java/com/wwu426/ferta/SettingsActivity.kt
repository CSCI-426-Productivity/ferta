package com.wwu426.ferta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var unavailableTimeButton: Button = findViewById(R.id.button_unavailability_change)
        unavailableTimeButton.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.main_scrollview, UnavailableFragment(), "tag").commit()
        }
    }
}