package com.example.gikhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.email
import kotlinx.android.synthetic.main.activity_sign_up.passwd

class SignUpActivity : AppCompatActivity() {
    var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signup_btn.setOnClickListener {
            signUp()
        }

    }
    fun signUp(){
        auth?.createUserWithEmailAndPassword(email.text.toString(),passwd.text.toString() )
            ?.addOnCompleteListener {
                    task->
                if(task.isSuccessful){
                    // Creating a user account
                    moveMainPage(task.result?.user)
                }else {
                    // Show the error message
                    Toast.makeText(this,task.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
    fun moveMainPage(user: FirebaseUser?){
        if(user!=null){
            val goHome = Intent(this, MainActivity::class.java)
            signup_btn.setOnClickListener{startActivity(goHome)}
            finish()
        }

    }

}