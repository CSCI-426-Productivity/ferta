package com.wwu426.ferta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_fragment, HomeFragment(), "tag").commit()
    }

    fun onClick(view : View) {
        if(view.id == R.id.button)
            supportFragmentManager.beginTransaction().replace(R.id.main_fragment, HomeFragment(), "tag").commit()
        if(view.id == R.id.button2)
            supportFragmentManager.beginTransaction().replace(R.id.main_fragment, TodayFragment(), "tag").commit()
        if(view.id == R.id.button3)
            supportFragmentManager.beginTransaction().replace(R.id.main_fragment, CalendarFragment(), "tag").commit()
        if(view.id == R.id.button4)
            startActivity(Intent(applicationContext, AddTaskActivity::class.java))
    }
}