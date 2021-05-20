package com.wwu426.ferta

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.*

class Task {
    lateinit var name: String
    lateinit var dueDate: Date
    lateinit var tag: String
    lateinit var description: String
    lateinit var sessions: Date
}

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