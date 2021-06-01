package com.wwu426.ferta

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        // fix lifecycle duplicating fragments bug, not ideal
        for (fragment in supportFragmentManager.fragments)
            supportFragmentManager.beginTransaction().remove(fragment).commit()

        supportFragmentManager.beginTransaction().add(
            R.id.add_task_fragment,
            AddSmartTaskFragment(),
            ""
        ).commit()

        if(ViewModelProvider(this).get(AddTaskViewModel::class.java).state == AddTaskViewModel.State.onAddTaskViewModel) {
            // go to next setup page
            supportFragmentManager.beginTransaction().replace(
                R.id.add_task_fragment,
                AddTechnicalTaskFragment(),
                ""
            ).addToBackStack(null).commit()
        }
    }
}