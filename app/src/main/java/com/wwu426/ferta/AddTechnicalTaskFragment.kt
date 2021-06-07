package com.wwu426.ferta

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_add_technical_task.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class AddTechnicalTaskFragment : Fragment() {

    private lateinit var nameEditText : EditText
    private lateinit var tagsEditText : EditText
    private lateinit var timeNeededEditText : EditText
    private lateinit var repeatCheckBox : CheckBox
    private lateinit var canConflictCheckBox : CheckBox
    private lateinit var sendNotifCheckBox : CheckBox
    private lateinit var endDateTextView : TextView
    private lateinit var useHoursSpinner : Spinner

    private val addTaskViewModel : AddTaskViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AddTaskViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_add_technical_task, container, false)

        // get views from layout
        nameEditText = layout.findViewById(R.id.task_name_edit_text)
        tagsEditText = layout.findViewById(R.id.tags_edit_text)
        timeNeededEditText = layout.findViewById(R.id.time_needed_for_completion_text_box)
        repeatCheckBox = layout.findViewById(R.id.repeat_event_checkBox)
        canConflictCheckBox = layout.findViewById(R.id.can_cause_conflict_checkBox)
        sendNotifCheckBox = layout.findViewById(R.id.send_notification_checkBox)
        endDateTextView = layout.findViewById(R.id.end_date_tv2)
        useHoursSpinner = layout.findViewById(R.id.time_needed_for_completion_spinner)

        // add listeners to some views
        repeatCheckBox.setOnClickListener {
            if(repeatCheckBox.isChecked) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "TODO: do something with this",
                    Toast.LENGTH_SHORT
                ).show()
                RepeatFragment().show(requireFragmentManager(), "f1")
            }
        }
        useHoursSpinner.onItemSelectedListener =
            object : OnItemSelectedListener {
                override fun onItemSelected(
                    parentView: AdapterView<*>?,
                    selectedItemView: View,
                    position: Int,
                    id: Long
                ) {
                    addTaskViewModel.task_time_in_hours = position == 1
                }

                override fun onNothingSelected(parentView: AdapterView<*>?) {
                    // your code here
                }
            }
        layout.findViewById<TextView>(R.id.end_date_tv2).setOnClickListener {
            val newFragment = DatePickerFragment() { date ->
                // Do something with the date chosen by the user
                ViewModelProvider(requireActivity()).get(AddTaskViewModel::class.java).task.dueDate = date
                endDateTextView.text = date
            }
            newFragment.show(requireFragmentManager(), "endTaskDatePicker")
        }
        sendNotifCheckBox.setOnClickListener {
            if(sendNotifCheckBox.isChecked)
                NotificationFragment().show(requireFragmentManager(), "f2")
        }
        layout.findViewById<Button>(R.id.cancel_task_finish_button).setOnClickListener {
            fragmentManager?.beginTransaction()?.setCustomAnimations(
                R.anim.slide_out_left, R.anim.slide_in_right
            )?.replace(
                R.id.add_task_fragment,
                AddSmartTaskFragment(),
                ""
            )?.addToBackStack(null)?.commit()
        }
        layout.findViewById<Button>(R.id.add_task_finish_button).setOnClickListener {

            if(task_name_edit_text.text.isBlank()) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Please enter a name",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else if(timeNeededEditText.text.isBlank()) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "Please enter time needed",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else{
                updateFromUI()
                requireActivity().setResult(RESULT_OK, Intent().apply {
                    putExtra(
                        "task",
                        addTaskViewModel.task
                    )
                })
                requireActivity().finish()
            }
        }

        // save state in view model
        addTaskViewModel.state = AddTaskViewModel.State.onAddTaskViewModel

        return layout
    }

    override fun onStart() {
        super.onStart()

        if (addTaskViewModel.task_time_in_hours)
            useHoursSpinner.setSelection(1)
        else
            useHoursSpinner.setSelection(0)

        // update UI from view model
        nameEditText.setText(addTaskViewModel.task.name)
        tagsEditText.setText(
            addTaskViewModel.task.tags.toString().replace("[", "").replace("]", "")
        )
        timeNeededEditText.setText(addTaskViewModel.task_time_needed)
        repeatCheckBox.isChecked = addTaskViewModel.task_repeat
        canConflictCheckBox.isChecked = addTaskViewModel.task_can_conflict
        sendNotifCheckBox.isChecked = addTaskViewModel.task_send_notif
        endDateTextView.text = addTaskViewModel.task.dueDate.toString()
    }

    override fun onStop() {
        super.onStop()

        updateFromUI()
    }
    private fun updateFromUI () {
        addTaskViewModel.task.name = nameEditText.text.toString()
        addTaskViewModel.task.tags = tagsEditText.text.toString().split(",|(, )").toMutableList()
        addTaskViewModel.task_time_needed = timeNeededEditText.text.toString()
        addTaskViewModel.task_repeat = repeatCheckBox.isChecked
        addTaskViewModel.task_can_conflict = canConflictCheckBox.isChecked
        addTaskViewModel.task_send_notif = sendNotifCheckBox.isChecked
        //addTaskViewModel.task.dueDate = endDateTextView.text.toString()
    }
}