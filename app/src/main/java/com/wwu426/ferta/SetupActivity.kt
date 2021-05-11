package com.wwu426.ferta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout

class SetupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup)

        supportFragmentManager.beginTransaction().add(R.id.setup_fragment, InitializationFragment(), "InitializationFragment").commit()
    }

    fun onClick(view : View) {
        if(view.id == R.id.next_init_button) {
            if(supportFragmentManager.findFragmentByTag("InitializationFragment") != null && supportFragmentManager.findFragmentByTag("InitializationFragment")!!.isVisible)
                supportFragmentManager.beginTransaction().replace(R.id.setup_fragment, UnavailableFragment(), "UnavailableFragment").commit()

            else if(supportFragmentManager.findFragmentByTag("UnavailableFragment") != null && supportFragmentManager.findFragmentByTag("UnavailableFragment")!!.isVisible)
                startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}