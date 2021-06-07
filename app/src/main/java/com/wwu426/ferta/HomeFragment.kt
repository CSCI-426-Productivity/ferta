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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
            holder.taskDueDate.text = taskList[position].dueDate
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

//====================== FRAGMENT STUFF =======================
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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
        //Toast.makeText(context, "tasks: ${ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java).tasks}", Toast.LENGTH_SHORT).show()
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        var recyclerList: RecyclerView = view.findViewById(R.id.home_list)
        var taskList = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java).tasks
        recyclerList.adapter = HomeListAdapter(taskList)
        recyclerList.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

