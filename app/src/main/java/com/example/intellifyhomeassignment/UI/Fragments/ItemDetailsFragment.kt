package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.intellifyhomeassignment.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_itemdetails.*
import kotlinx.android.synthetic.main.user_lists.view.*

class ItemDetailsFragment : Fragment(R.layout.fragment_itemdetails) {

    val args:ItemDetailsFragment by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

logout.setOnClickListener {
    FirebaseAuth.getInstance().signOut()
    findNavController().navigate(R.id.action_itemDetailsFragment_to_loginFragment)
}


    }
}