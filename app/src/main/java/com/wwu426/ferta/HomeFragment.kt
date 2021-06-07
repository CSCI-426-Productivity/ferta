package com.wwu426.ferta

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var htag = "HOME"

//====================== RECYCLER STUFF =======================

class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var contentContainer: LinearLayout = view.findViewById(R.id.task_container_home)
    var taskName: TextView = view.findViewById(R.id.task_name_home)
    var taskDueDate: TextView = view.findViewById(R.id.task_due_date)
    val view = view
}

//Model that adapts the list of items to the recycler view
class HomeListAdapter(val taskList: MutableList<Task>) : RecyclerView.Adapter<HomeViewHolder>() {
    //Tell it how many items are in the list
    override fun getItemCount(): Int {
        return taskList.size
    }

    // Called when recycler view decides it needs a viewholder to hold items on the screen
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_home_list, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        // Need to check size or the app crashes. Opens new activity
        // displaying details when clicked on
        if (taskList.size > 0) {
            val description = taskList[position].description
            holder.taskName.text = taskList[position].name
            holder.taskDueDate.text = taskList[position].dueDate.toString()
            holder.contentContainer.setOnClickListener {
                Log.d(htag, "Clicked on an item $position")
                val intent = Intent(holder.view.context, TaskDetails::class.java)
                with (intent) {
                    putExtra("NAME", holder.taskName.text.toString())
                    putExtra("DATE", holder.taskDueDate.text.toString())
                    putExtra("DESCRIPTION", description)
                    putExtra("INDEX", position)
                    holder.view.context.startActivity(this)
                }
            }
        }
    }
}

class HomeFragment : Fragment() {

    private lateinit var adapter: HomeListAdapter
    private lateinit var dateTV : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = HomeListAdapter(ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java).tasks)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //Toast.makeText(context, "tasks: ${ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java).tasks}", Toast.LENGTH_SHORT).show()
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        dateTV = view.findViewById(R.id.text_welcome)
        var recyclerList: RecyclerView = view.findViewById(R.id.home_list)
        recyclerList.adapter = adapter
        recyclerList.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dateTV.text = "Welcome back!\n${LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-yyyy"))}"
        }
        adapter.notifyDataSetChanged()
    }
}

