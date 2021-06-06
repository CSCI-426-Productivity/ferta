package com.wwu426.ferta

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.wwu426.ferta.Meridiem
import com.wwu426.ferta.Weekday
import java.time.LocalTime

data class UnavailableTime(var weekday: Weekday, var startTime: LocalTime, var endTime: LocalTime) {

    // SETTERS
    @RequiresApi(Build.VERSION_CODES.O)
    fun setStartTimeMinutes(minutes : Int) {
        startTime = startTime.withMinute(minutes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setStartTimeHours(hours : Int) {
        startTime = startTime.withHour(hours)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEndTimeMinutes(minutes : Int) {
        endTime = endTime.withMinute(minutes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEndTimeHours(hours : Int) {
        endTime = endTime.withHour(hours)
    }


    // GETTERS
    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextStartTimeHours() : String {
        return startTime.hour.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextStartTimeMinutes() : String {
        return startTime.minute.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextEndTimeHours() : String {
        return endTime.hour.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextEndTimeMinutes() : String {
        return endTime.minute.toString()
    }

}