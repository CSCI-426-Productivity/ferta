package com.wwu426.ferta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        supportFragmentManager.beginTransaction().add(R.id.add_task_fragment, AddSmartTaskFragment(), "").commit()
    }
}