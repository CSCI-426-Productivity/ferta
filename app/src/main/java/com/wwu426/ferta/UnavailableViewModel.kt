package com.wwu426.ferta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UnavailableViewModel(): ViewModel() {

    companion object {
        private val unavailableTimeList = mutableListOf<UnavailableTime>()
    }

    fun addUnavailableTime(unavailableTime: UnavailableTime) {
        unavailableTimeList.add(unavailableTime)
    }

    fun removeUnavailableTime(unavailableTime: UnavailableTime) {
        for (item in unavailableTimeList) {
            if (item.weekday == unavailableTime.weekday && item.startTime == unavailableTime.startTime && item.endTime == unavailableTime.endTime) {
                val ret = unavailableTimeList.remove(unavailableTime)
                if (!ret) {
                    Log.e("UTDao", "removeUnavailableTime: Could not find and delete this time.")
                }
            }
        }
    }

    fun getUnavailableTimes() = unavailableTimeList
}