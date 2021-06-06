package com.wwu426.ferta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TaskDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_details)

        val taskNameTV: TextView = findViewById(R.id.details_task_id)
        val taskDueDateTV: TextView = findViewById(R.id.details_due_date)
        val taskTagsTV: TextView = findViewById(R.id.details_task_tags)
        val taskDescriptionTV: TextView = findViewById(R.id.details_task_description)
        val taskSessionsTV: TextView = findViewById(R.id.details_task_sessions)
        val deleteButton: Button = findViewById(R.id.details_delete_button)

        val taskName = intent.getStringExtra("NAME")
        val taskDueDate = intent.getStringExtra("DATE")
        val taskDescription = intent.getStringExtra("DESCRIPTION")

        taskNameTV.text = taskName
        taskDueDateTV.text = "Task: $taskDueDate"
        taskDescriptionTV.text = "Description: \n\n$taskDescription"


        deleteButton.setOnClickListener {
            finish()
        }

    }
}