package com.wwu426.ferta

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

class NotificationFragment :  DialogFragment() {
    fun newInstance(): RepeatFragment? {
        return RepeatFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pick a style based on the num.
        var style = STYLE_NORMAL
        setStyle(style, theme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_notification, container, false)
        //val tv: View = v.findViewById(R.id.text)
        //(tv as TextView).text = "a different howdy"

        return v
    }
}