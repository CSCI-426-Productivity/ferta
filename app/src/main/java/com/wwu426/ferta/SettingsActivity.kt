package com.wwu426.ferta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        supportFragmentManager.beginTransaction().replace(R.id.settings_view, SettingsFragment(), "Settings").commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings_back_button -> {
                val settingsFragment = supportFragmentManager.findFragmentById(R.id.settings_view)
                val unavailabilityFragment = supportFragmentManager.findFragmentById(R.id.fragment_unavailable)
                if (settingsFragment != null && settingsFragment.isVisible) {
                    this.finish()
                } else if (unavailabilityFragment != null && unavailabilityFragment.isVisible) {
                    supportFragmentManager.beginTransaction().replace(R.id.settings_view, SettingsFragment(), "Settings").commit()
                } else {
                    Log.e("SETTAG", "onOptionsItemSelected: Fragment for Settings back button could not be found")
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}