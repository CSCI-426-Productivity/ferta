package com.wwu426.ferta

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class MainActivityViewModel : ViewModel() {
    val tasks : MutableList<Task> = mutableListOf()
}