package com.wwu426.ferta

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import java.nio.Buffer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddTechnicalTaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTechnicalTaskFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_add_technical_task, container, false)

        val c1 = layout.findViewById<CheckBox>(R.id.repeat_event_checkBox)
        c1.setOnClickListener {
            if(c1.isChecked)
                RepeatFragment().show(fragmentManager!!, "f1")
        }

        layout.findViewById<TextView>(R.id.end_date_tv2).setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "TODO: make this actually work", Toast.LENGTH_SHORT).show()
            val newFragment = DatePickerFragment()
            newFragment.show(fragmentManager!!, "endTaskDatePicker")
        }

        val c2 = layout.findViewById<CheckBox>(R.id.send_notification_checkBox)
        c2.setOnClickListener {
            if(c2.isChecked)
                NotificationFragment().show(fragmentManager!!, "f2")
        }

        layout.findViewById<Button>(R.id.cancel_task_finish_button).setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "TODO: cancel", Toast.LENGTH_SHORT).show()
        }

        layout.findViewById<Button>(R.id.add_task_finish_button).setOnClickListener {
            Toast.makeText(activity!!.applicationContext, "TODO: add and go back to home", Toast.LENGTH_SHORT).show()
        }

        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddTechnicalTaskFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTechnicalTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}