package com.wwu426.ferta

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalTime

class UnavailableTime {

    constructor()

    private val TAG: String = "UNTIME"

    // VARIABLES
    var weekday : Weekday = Weekday.SUNDAY
    // Start times of unavailability
    @RequiresApi(Build.VERSION_CODES.O)
    var startTime : LocalTime = LocalTime.MIN
    var startMeridiem = Meridiem.AM
    // End times of unavailability
    @RequiresApi(Build.VERSION_CODES.O)
    var endTime : LocalTime = LocalTime.MAX
    var endMeridiem = Meridiem.PM


    // SETTERS
    @RequiresApi(Build.VERSION_CODES.O)
    fun setStartTimeMinutes(minutes : Int) {
        startTime = startTime.withMinute(minutes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setStartTimeHours(hours : Int) {
        if (hours in 12..23) {
            startMeridiem = Meridiem.PM
        } else if (hours in 0..11) {
            startMeridiem = Meridiem.AM
        } else {
            Log.e(TAG, "setStartTimeHours: Hour '$hours' is out of range (0-23)")
        }
        startTime = startTime.withHour(hours)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEndTimeMinutes(minutes : Int) {
        endTime = endTime.withMinute(minutes)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setEndTimeHours(hours : Int) {
        if (hours in 12..23) {
            endMeridiem = Meridiem.PM
        } else if (hours in 0..11) {
            endMeridiem = Meridiem.AM
        } else {
            Log.e(TAG, "setStartTimeHours: Hour '$hours' is out of range (0-23)")
        }
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

    fun getStartMeridiem() : Boolean {
        return startMeridiem == Meridiem.PM
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextEndTimeHours() : String {
        return endTime.hour.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getTextEndTimeMinutes() : String {
        return endTime.minute.toString()
    }

    fun getEndMeridiem() : Boolean {
        return endMeridiem == Meridiem.PM
    }

}