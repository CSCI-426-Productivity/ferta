package com.wwu426.ferta

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.view.*
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
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
        val activity = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.main_fragment, HomeFragment(), "tag").commit()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            selectNavButton("home")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun selectNavButton(str : String) {

        nav_home_button.setBackgroundColor(getColor(R.color.nav_unselected_background))
        nav_today_button.setBackgroundColor(getColor(R.color.nav_unselected_background))
        nav_calendar_button.setBackgroundColor(getColor(R.color.nav_unselected_background))

        when(str) {
            "home" -> nav_home_button.setBackgroundColor(getColor(R.color.nav_selected_background))
            "today" -> nav_today_button.setBackgroundColor(getColor(R.color.nav_selected_background))
            "calendar" -> nav_calendar_button.setBackgroundColor(getColor(R.color.nav_selected_background))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun onClick(view : View) {
        if(view.id == R.id.nav_home_button) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, HomeFragment(), "tag").commit()
            selectNavButton("home")
        }
        if(view.id == R.id.nav_today_button) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, TodayFragment(), "tag").commit()
            selectNavButton("today")
        }

        if(view.id == R.id.nav_calendar_button) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, CalendarFragment(), "tag").commit()
            selectNavButton("calendar")
        }

        if(view.id == R.id.nav_add_button)
            startActivityForResult(Intent(applicationContext, AddTaskActivity::class.java), RC_ADD_TASK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_ADD_TASK && resultCode == RESULT_OK) {
            val newTask = data!!.getParcelableExtra<Task>("task")
            ViewModelProvider(this).get(MainActivityViewModel::class.java).tasks.add(newTask!!)
            //Toast.makeText(applicationContext, "In MainActivity, got new task: ${newTask!!.name}, ${newTask.description}, ${newTask.dueDate}, ${newTask.tags}", Toast.LENGTH_LONG).show()
        }
    }
}