package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.intellifyhomeassignment.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_itemdetails.*

class ItemDetailsFragment : Fragment(R.layout.fragment_itemdetails) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val args: ItemDetailsFragmentArgs by navArgs()

        actualName.text=args.user.name
        actualEmail.text=args.user.email
        actualCity.text=args.user.city
        actualAge.text=args.user.age

        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_itemDetailsFragment_to_loginFragment)
        }


    }
}