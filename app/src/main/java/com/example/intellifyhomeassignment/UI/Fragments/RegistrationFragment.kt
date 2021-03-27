package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.UI.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_registration.*

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
        val selectedId=radioGroup.checkedRadioButtonId
        var gender="male"
        when(selectedId)
        {
            Male.id->gender="Male"
            Female.id->gender="Female"
            Other.id->gender="Other"
        }
        val city = editTextCity.text.toString().trim()
        Log.d("GENDER", "gender : $gender")

        if (name.isEmpty()) {
            edittextViewName.error = "Full Name is required"
            edittextViewName.requestFocus()
            return
        }
        if (age.isEmpty()) {
            edittextViewAge.error = "Age is Required"
            edittextViewAge.requestFocus()
            return
        }
        if (email.isEmpty()) {
            edittextEmail.error = "Email is Required"
            edittextEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edittextEmail.error = "Please Provide Valid Email"
            edittextEmail.requestFocus()
            return
        }
        if (password.isEmpty()) {
            editTextPassword.error = "Passsword is empty"
            editTextPassword.requestFocus()
            return
        }
        if (password.length < 6) {
            editTextPassword.error = "Enter password more than 6 characters"
            editTextPassword.requestFocus()
            return
        }
        if (city.isEmpty()) {
            editTextCity.error = "Please Enter City where you live"
            editTextCity.requestFocus()
            return
        }
        progressBar1.visibility = View.VISIBLE
        editTextCity.clearFocus()
        editTextPassword.clearFocus()
        edittextEmail.clearFocus()
        edittextViewAge.clearFocus()
        edittextViewName.clearFocus()

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = User(name, city, age, gender, email, password)
                    FirebaseDatabase.getInstance().getReference("User")
                        .child(FirebaseAuth.getInstance().currentUser.uid).setValue(user)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Successfully Registered Congrats ",
                                    Toast.LENGTH_LONG
                                ).show()
                                progressBar1.visibility = View.GONE
                            } else {
                                Toast.makeText(
                                    context,
                                    "Failed To Register, Try Again ",
                                    Toast.LENGTH_LONG
                                ).show()
                                progressBar1.visibility = View.GONE
                            }
                        }
                } else {
                    Toast.makeText(context, "Failed To Register, Try Again ", Toast.LENGTH_LONG)
                        .show()
                    progressBar1.visibility = View.GONE
                }
            }


    }
}