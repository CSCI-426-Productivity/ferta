package com.wwu426.ferta

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Task (
    var name: String,
    var dueDate: String,
    var tags: MutableList<String>,
    var notificationsBefore: MutableList<String>,
    var description: String,
    var sessions: Date?
) : Parcelable

class MainActivity : AppCompatActivity() {

    private val RC_ADD_TASK = 1

    companion object {
        var testTasks = mutableListOf<Task>()
    }

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
            startActivityForResult(Intent(applicationContext, AddTaskActivity::class.java), RC_ADD_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_ADD_TASK && resultCode == RESULT_OK) {
            val newTask = data!!.getParcelableExtra<Task>("task")
            ViewModelProvider(this).get(MainActivityViewModel::class.java).tasks.add(newTask!!)
            testTasks.add(newTask)
            //Toast.makeText(applicationContext, "In MainActivity, got new task: ${newTask!!.name}, ${newTask.description}, ${newTask.dueDate}, ${newTask.tags}", Toast.LENGTH_LONG).show()
        }
    }
}