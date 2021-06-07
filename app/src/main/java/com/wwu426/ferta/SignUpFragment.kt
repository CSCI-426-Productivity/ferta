package com.wwu426.ferta

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

class SignUpFragment : Fragment() {

    @SuppressLint("CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var layout = inflater.inflate(R.layout.fragment_sign_up, container, false)

        layout.findViewById<Button>(R.id.finish_signup_button).setOnClickListener {


            val password1 = layout.findViewById<TextView>(R.id.signin_password1).text.toString()
            val password2 = layout.findViewById<TextView>(R.id.signin_password2).text.toString()
            if(layout.findViewById<TextView>(R.id.signin_email_et).text.isBlank()){
                Toast.makeText(requireContext(), "Please enter an email", LENGTH_SHORT).show()
            }
            else if(password1.isBlank() || password2.isBlank() || password1 != password2){
                Toast.makeText(requireContext(), "Please enter matching passwords", LENGTH_SHORT).show()
            }
            else {
                layout.findViewById<TextView>(R.id.signin_email_et).text = ""
                layout.findViewById<TextView>(R.id.signin_password1).text = ""
                layout.findViewById<TextView>(R.id.signin_password2).text = ""
                startActivity(Intent(context, SetupActivity::class.java))
            }
        }

        layout.findViewById<Button>(R.id.signup_back_button).setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(
                R.id.auth_fragment,
                LoginFragment(),
                "loginFrag"
            )?.commit()
        }

        return layout
    }

}