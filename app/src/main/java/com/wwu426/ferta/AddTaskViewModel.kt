package com.wwu426.ferta

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
class AddTaskViewModel : ViewModel() {

    // Handling visible view
    enum class State {
        onAddSMART,
        onAddTaskViewModel
    }
    var state : State = State.onAddSMART

    // SMART setup
    var isMeasureable : Boolean = false
    var isAttainable : Boolean = false
    var isRelevant : Boolean = false

    // task details
    val task : Task by lazy {
        Task("",
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")),
            mutableListOf(),
            mutableListOf(),
            "",
            null)
    }

    var task_time_needed : String = ""
    var task_time_in_hours : Boolean = false
    var task_repeat : Boolean = false
    var task_can_conflict : Boolean = false
    var task_send_notif : Boolean = false
}