package com.wwu426.ferta

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class UnavailableTimeAdapter(private val weekday: Weekday) : RecyclerView.Adapter<UnavailableTimeAdapter.ViewHolder>() {

    private var dataSet = mutableListOf<UnavailableTime>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val startHourTimeEdit: EditText = view.findViewById(R.id.start_hour_number)
        val startMinuteTimeEdit: EditText = view.findViewById(R.id.start_minute_number)
        val startAMPMToggle: ToggleButton = view.findViewById(R.id.start_toggle_ampm)
        val endHourTimeEdit: EditText = view.findViewById(R.id.end_hour_number)
        val endMinuteTimeEdit: EditText = view.findViewById(R.id.end_minute_number)
        val endAMPMToggle: ToggleButton = view.findViewById(R.id.end_toggle_ampm)

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_unavailable_time, parent, false)

        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.startHourTimeEdit.setText(dataSet[position].getTextStartTimeHours())
        holder.startMinuteTimeEdit.setText(dataSet[position].getTextStartTimeMinutes())
        holder.startAMPMToggle.isChecked = dataSet[position].getStartMeridiem()
        holder.endHourTimeEdit.setText(dataSet[position].getTextEndTimeHours())
        holder.endMinuteTimeEdit.setText(dataSet[position].getTextEndTimeMinutes())
        holder.endAMPMToggle.isChecked = dataSet[position].getEndMeridiem()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}