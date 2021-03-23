package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.UI.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registration.*
import java.util.regex.Pattern

class RegistrationFragment : Fragment(R.layout.fragment_registration), View.OnClickListener {
    lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        registerbutton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.registerbutton -> registerUser()
        }
    }

    private fun registerUser() {
        val name = edittextViewName.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val email = edittextEmail.text.toString().trim()
        val age = edittextViewAge.text.toString().trim()
        val gender = radioGroup.checkedRadioButtonId.toString().trim()
        val city = editTextCity.text.toString().trim()
        Log.d("GENDER", "gender : $gender")

        if (name.isEmpty()) {
            edittextViewName.setError("Full Name is required")
            edittextViewName.requestFocus()
            return
        }
        if (age.isEmpty()) {
            edittextViewAge.setError("Age is Required")
            edittextViewAge.requestFocus()
            return
        }
        if (email.isEmpty()) {
            edittextEmail.setError("Email is Required")
            edittextEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edittextEmail.setError("Please Provide Valid Email")
            edittextEmail.requestFocus()
            return
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Passsword is empty")
            editTextPassword.requestFocus()
            return
        }
        if (password.length < 6) {
            editTextPassword.setError("Enter password more than 6 characters")
            editTextPassword.requestFocus()
            return
        }
        if (city.isEmpty()) {
            editTextCity.setError("Please Enter City where you live")
            editTextCity.requestFocus()
            return
        }
        progressBar.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {
                        val user = User(name, city, age, gender, email, password)
                        FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().currentUser.uid).setValue(user)
                            .addOnCompleteListener(object : OnCompleteListener<Void> {
                                override fun onComplete(task: Task<Void>) {
                                    if (task.isSuccessful) {
                                        Toast.makeText(
                                            context,
                                            "Successfully Registered Congrats ",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        progressBar.visibility = View.GONE
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Failed To Register, Try Again ",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        progressBar.visibility = View.GONE
                                    }
                                }
                            })
                    } else {
                        Toast.makeText(context, "Failed To Register, Try Again ", Toast.LENGTH_LONG)
                            .show()
                        progressBar.visibility = View.GONE
                    }
                }

            })


    }
}