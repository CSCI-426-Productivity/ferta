package com.wwu426.ferta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class AddSmartTaskFragment : Fragment() {

    private lateinit var specificTextEditText : EditText
    private lateinit var isMeasureableCheckBox : CheckBox
    private lateinit var isAttainableCheckBox : CheckBox
    private lateinit var isRelevantCheckBox : CheckBox
    private lateinit var dateTextView : TextView
    private val addTaskViewModel : AddTaskViewModel by lazy {
        ViewModelProvider(requireActivity()).get(AddTaskViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_add_smart_task, container, false)

        // get views from layout
        specificTextEditText = layout.findViewById(R.id.specific_edit_text)
        isMeasureableCheckBox = layout.findViewById(R.id.measureable_checkBox)
        isAttainableCheckBox = layout.findViewById(R.id.attainable_checkBox)
        isRelevantCheckBox = layout.findViewById(R.id.relevent_checkBox)
        dateTextView = layout.findViewById(R.id.end_date_tv)

        // add listeners to some views
        layout.findViewById<TextView>(R.id.end_date_tv).setOnClickListener {
            val newFragment = DatePickerFragment() { date ->
                // Do something with the date chosen by the user
                ViewModelProvider(requireActivity()).get(AddTaskViewModel::class.java).task.dueDate = date
                dateTextView.text = date
            }
            newFragment.show(requireFragmentManager(), "endTaskDatePicker")
        }
        layout.findViewById<Button>(R.id.cancel_task_finish_button).setOnClickListener {
            requireActivity().finish()
        }
        layout.findViewById<Button>(R.id.add_task_next_button).setOnClickListener {
            // go to next setup page
            fragmentManager?.beginTransaction()?.replace(
                R.id.add_task_fragment,
                AddTechnicalTaskFragment(),
                ""
            )?.addToBackStack(null)?.commit()
        }

        // save state in view model
        addTaskViewModel.state = AddTaskViewModel.State.onAddSMART

        return layout
    }

    override fun onStart() {
        super.onStart()

        // update UI from view model
        specificTextEditText.setText(addTaskViewModel.task.description)
        isMeasureableCheckBox.isChecked = addTaskViewModel.isMeasureable
        isAttainableCheckBox.isChecked = addTaskViewModel.isAttainable
        isRelevantCheckBox.isChecked = addTaskViewModel.isRelevant
        dateTextView.text = addTaskViewModel.task.dueDate
    }

    override fun onStop() {
        super.onStop()


        // update view model from UI
        addTaskViewModel.task.description = specificTextEditText.text.toString()
        addTaskViewModel.isMeasureable = isMeasureableCheckBox.isChecked
        addTaskViewModel.isAttainable = isAttainableCheckBox.isChecked
        addTaskViewModel.isRelevant = isRelevantCheckBox.isChecked
        addTaskViewModel.task.dueDate = dateTextView.text.toString()
    }
}