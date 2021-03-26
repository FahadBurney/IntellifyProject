package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.intellifyhomeassignment.R
import com.example.intellifyhomeassignment.R.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_forgetpassword.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.editTextPassword
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_login.textRegisterButton as textRegisterButton1

class LoginFragment : Fragment(layout.fragment_login), View.OnClickListener {
    lateinit var mAuth:FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textRegisterButton1.setOnClickListener(this)
        LoginButton.setOnClickListener(this)
        textForgotPassword.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.textRegisterButton ->
                findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
            R.id.LoginButton ->
                userLoginVerification()
            R.id.textForgotPassword->
                findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)

        }

    }



    private fun userLoginVerification() {
        val email = editTextEmailAddress.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        if (email.isEmpty()) {
            edittextEmail.error = "Email is Required"
            edittextEmail.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmailAddress.error = "Please Provide Valid Email"
            editTextEmailAddress.requestFocus()
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
        progressBar.visibility=View.VISIBLE

        mAuth= FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(object :OnCompleteListener<AuthResult>{
            override fun onComplete(task: Task<AuthResult>) {
if(task.isSuccessful)
{
    progressBar.visibility=View.GONE
val user:FirebaseUser=FirebaseAuth.getInstance().currentUser
    if(user.isEmailVerified) {
        findNavController().navigate(R.id.action_loginFragment_to_userListFragment)
    }
    else
    {
        progressBar.visibility=View.GONE
        user.sendEmailVerification()
        Toast.makeText(context,"Check your Email Address to verify",Toast.LENGTH_SHORT).show()
    }
    }
else{
    progressBar.visibility=View.GONE
    Toast.makeText(context,"Failed To Login Please Check Your Credentials",Toast.LENGTH_SHORT).show()
}
            }

        })
    }
}