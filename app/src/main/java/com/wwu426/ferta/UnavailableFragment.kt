package com.wwu426.ferta

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalTime

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

// RecyclerView
class UnavailableTimeAdapter(private val unavailableTimesList: MutableList<UnavailableTime>) : RecyclerView.Adapter<UnavailableTimeAdapter.UnavailableViewHolder>() {

    class UnavailableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var editStartHours = view.findViewById<EditText>(R.id.start_hour_number)
        var editStartMinutes = view.findViewById<EditText>(R.id.start_minute_number)
        var toggleStartMeridiem = view.findViewById<ToggleButton>(R.id.start_toggle_ampm)
        var editEndHours = view.findViewById<EditText>(R.id.end_hour_number)
        var editEndMinutes = view.findViewById<EditText>(R.id.end_minute_number)
        var toggleEndMeridiem = view.findViewById<ToggleButton>(R.id.end_toggle_ampm)

        init {

        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UnavailableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_unavailable_time, parent, false)
        return UnavailableViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UnavailableViewHolder, position: Int) {
        var startHours = unavailableTimesList[position].startTime.hour
        var endHours = unavailableTimesList[position].endTime.hour

        holder.toggleStartMeridiem.isChecked = false
        holder.toggleEndMeridiem.isChecked = false
        if (startHours > 11) {
            startHours = unavailableTimesList[position].startTime.hour - 12
            holder.toggleStartMeridiem.isChecked = true
        }
        if (endHours > 11) {
            endHours = unavailableTimesList[position].endTime.hour - 12
            holder.toggleEndMeridiem.isChecked = true
        }
        holder.editStartHours.setText(startHours.toString())
        holder.editEndHours.setText(endHours.toString())
        holder.editStartMinutes.setText(unavailableTimesList[position].startTime.minute.toString())
        holder.editEndMinutes.setText(unavailableTimesList[position].endTime.minute.toString())
    }

    override fun getItemCount(): Int {
        return unavailableTimesList.size
    }

}

/**
 * A simple [Fragment] subclass.
 * Use the [UnavailableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UnavailableFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var rvSunday: RecyclerView
    private lateinit var rvAdapter: UnavailableTimeAdapter
    private lateinit var unavailableViewModel: UnavailableViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        unavailableViewModel = ViewModelProvider(this).get(UnavailableViewModel::class.java)
        rvAdapter = UnavailableTimeAdapter(unavailableViewModel.getUnavailableTimes())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(R.layout.fragment_unavailable, container, false)
        val sundayAddButton: Button = layout.findViewById(R.id.button_sunday_add_time)

        rvSunday = layout.findViewById(R.id.unavailable_sundays_recycler)
        rvSunday.layoutManager = LinearLayoutManager(context)
        rvSunday.adapter = rvAdapter

        sundayAddButton.setOnClickListener {
            unavailableViewModel.addUnavailableTime(UnavailableTime(Weekday.SUNDAY, LocalTime.MIN, LocalTime.MAX))
            rvAdapter.notifyDataSetChanged()
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
         * @return A new instance of fragment UnavailableFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UnavailableFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}