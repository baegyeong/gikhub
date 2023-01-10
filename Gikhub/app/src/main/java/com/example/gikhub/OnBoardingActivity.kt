package com.example.gikhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        val goLogin = Intent(this, LoginActivity::class.java)
        login_btn.setOnClickListener{ startActivity(goLogin) }

        val goSignUp = Intent(this, SignUpActivity::class.java)
        signup_btn.setOnClickListener{startActivity(goSignUp)}

    }
}