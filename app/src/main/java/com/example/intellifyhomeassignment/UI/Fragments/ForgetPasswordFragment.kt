package com.example.intellifyhomeassignment.UI.Fragments

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.intellifyhomeassignment.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_forgetpassword.*

class ForgetPasswordFragment : Fragment(R.layout.fragment_forgetpassword) {
    lateinit var mAuth:FirebaseAuth
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
mAuth= FirebaseAuth.getInstance()
        resetPassword.setOnClickListener(object :View.OnClickListener{
            override fun onClick(view: View) {
                goResetPasswordFirst()
            }
        })
    }

    private fun goResetPasswordFirst() {
        mAuth = FirebaseAuth.getInstance()
        val email = EmailEditText.text.toString().trim()

        if (email.isEmpty()) {
            EmailEditText.error = "Email is Required"
            EmailEditText.requestFocus()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            EmailEditText.error = "Please Provide Valid Email"
            EmailEditText.requestFocus()
            return
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(object :OnCompleteListener<Void>{
            override fun onComplete(task: Task<Void>) {
if(task.isSuccessful){
    ProgressBar.visibility=View.INVISIBLE

    Toast.makeText(context,"Check your Email for Password Resetting",Toast.LENGTH_SHORT).show()
}
                else{
    ProgressBar.visibility=View.INVISIBLE
    Toast.makeText(context,"Try Again Something Wrong Happened",Toast.LENGTH_SHORT).show()
}

            }

        })
    }
}