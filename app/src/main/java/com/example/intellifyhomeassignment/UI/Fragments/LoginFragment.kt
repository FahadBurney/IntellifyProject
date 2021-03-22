package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.R.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.textRegisterButton as textRegisterButton1

class LoginFragment : Fragment(layout.fragment_login), View.OnClickListener {
    lateinit var register:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textRegisterButton1.setOnClickListener(this)
}

    override fun onClick(view: View) {
when(view.id)
{
   R.id.textRegisterButton ->
        findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
}

    }
}