package com.wwu426.ferta

import android.app.ActionBar
import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider

val tdtag = "TASK_DETAILS"
class TaskDetails : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        val taskNameTV: TextView = findViewById(R.id.details_task_id)
        val taskDueDateTV: TextView = findViewById(R.id.details_due_date)
        val taskTagsTV: TextView = findViewById(R.id.details_task_tags)
        val taskDescriptionTV: TextView = findViewById(R.id.details_task_description)
        val taskSessionsTV: TextView = findViewById(R.id.details_task_sessions)
        val deleteButton: Button = findViewById(R.id.details_delete_button)
//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow)

        val taskName = intent.getStringExtra("NAME")
        val taskDueDate = intent.getStringExtra("DATE")
        val taskDescription = intent.getStringExtra("DESCRIPTION")
        val position = intent.getIntExtra("INDEX", -1)

//        toolbar.setNavigationOnClickListener {
//
//        }

        taskNameTV.text = taskName
        taskDueDateTV.text = "Task: $taskDueDate"
        taskDescriptionTV.text = "Description: \n\n$taskDescription"

        //deletes the task from the list
        deleteButton.setOnClickListener {
            finish()
        }

    }
}