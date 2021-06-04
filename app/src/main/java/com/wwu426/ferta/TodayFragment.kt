package com.wwu426.ferta

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//=================== RECYCLER STUFF =========================

var tag = "DAILY"
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var contentContainer: LinearLayout = view.findViewById(R.id.task_container)
    var taskItem: TextView = view.findViewById(R.id.task_name)
    var taskTime: TextView = view.findViewById(R.id.task_time)

    init {
        contentContainer.setOnClickListener {
            Log.d(tag, "Clicked on an item")
        }
    }
}

//Model that adapts the list of items to the recycler view
class MyListAdapter : RecyclerView.Adapter<ViewHolder>() {
    //Tell it how many items are in the list
    override fun getItemCount(): Int {
        return 100
    }

    // Called when recycler view decides it needs a viewholder to hold items on the screen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_today_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskItem.text = position.toString()
        holder.taskTime.text = position.toString()
    }
}

//=================== FRAGMENT STUFF =========================

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TodayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TodayFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}