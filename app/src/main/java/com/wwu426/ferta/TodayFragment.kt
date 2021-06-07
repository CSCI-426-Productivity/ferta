package com.wwu426.ferta

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//=================== RECYCLER STUFF =========================

var dtag = "DAILY"
class DailyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var contentContainer: LinearLayout = view.findViewById(R.id.task_container_daily)
    var taskName: TextView = view.findViewById(R.id.task_name_today)
    var taskTime: TextView = view.findViewById(R.id.task_time)
    val view = view
}

//Model that adapts the list of items to the recycler view
class DailyListAdapter(val taskList: MutableList<Task>) : RecyclerView.Adapter<DailyViewHolder>() {
    //Tell it how many items are in the list
    override fun getItemCount(): Int {
        return taskList.size
    }

    // Called when recycler view decides it needs a viewholder to hold items on the screen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_today_list, parent, false)
        return DailyViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        // Need to check size or the app crashes. Opens new activity
        // displaying details when clicked on
        if (taskList.size > 0) {
            val description = taskList[position].description
            holder.taskName.text = taskList[position].name
            val dueDate = taskList[position].dueDate
            holder.contentContainer.setOnClickListener {
                Log.d(htag, "Clicked on an item $position")
                val intent = Intent(holder.view.context, TaskDetails::class.java)
                with (intent) {
                    putExtra("NAME", holder.taskName.text.toString())
                    putExtra("DATE", dueDate)
                    putExtra("DESCRIPTION", description)
                    putExtra("INDEX", position)
                    holder.view.context.startActivity(this)
                }
            }
        }
    }
}

//=================== FRAGMENT STUFF =========================

class TodayFragment : Fragment() {
    private lateinit var adapter: DailyListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var taskList = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java).tasks
        adapter = DailyListAdapter(taskList)
        Log.d(dtag, "clicked on today")
        Log.d(dtag, taskList.size.toString())
        if (taskList.size > 0) {
            Log.d(dtag, taskList[0].name)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_today, container, false)
        var recyclerList: RecyclerView = view.findViewById(R.id.today_list)
        recyclerList.adapter = adapter
        recyclerList.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}