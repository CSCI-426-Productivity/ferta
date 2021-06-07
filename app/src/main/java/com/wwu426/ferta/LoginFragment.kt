package com.wwu426.ferta

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout = inflater.inflate(R.layout.fragment_login, container, false)

        layout.findViewById<Button>(R.id.login_button).setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }

        layout.findViewById<Button>(R.id.signup_button).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(
                R.id.auth_fragment,
                SignUpFragment(),
                "signupFrag"
            )?.commit()
        }

        return layout
    }
}