package com.wwu426.ferta

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import java.text.SimpleDateFormat
import java.util.*


class CalendarFragment : Fragment() {

    lateinit var compactCalendarView : CompactCalendarView
    lateinit var calendar_month_name_tv : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_calendar, container, false)

        compactCalendarView = layout.findViewById(R.id.compactcalendar_view) as CompactCalendarView
        calendar_month_name_tv = layout.findViewById(R.id.calendar_month_name_tv)

        return layout
    }

    @SuppressLint("SimpleDateFormat")
    fun showMonthName(date: Date) {
        calendar_month_name_tv.text = SimpleDateFormat("LLLL yyyy").format(date)
    }

    override fun onStart() {

        val viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        compactCalendarView.setFirstDayOfWeek(Calendar.SUNDAY)

        for(task in viewModel.tasks)
            compactCalendarView.addEvent(Event(Color.GREEN, task.dueDate.time, task.name))

        showMonthName(Calendar.getInstance().time)

        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events = compactCalendarView.getEvents(dateClicked)
                if(events.isNotEmpty())
                    Toast.makeText(requireContext(), "Tasks: $events", Toast.LENGTH_SHORT).show()
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                showMonthName(firstDayOfNewMonth)
            }
        })

        super.onStart()
    }
}